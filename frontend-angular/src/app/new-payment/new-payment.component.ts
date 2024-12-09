import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { PaymentType } from '../model/students.model';
import { StudentService } from '../services/student.service';

@Component({
  selector: 'app-new-payment',
  templateUrl: './new-payment.component.html',
  styleUrl: './new-payment.component.css'
})
export class NewPaymentComponent implements OnInit {


  paymentFormGroup!: FormGroup
  showProgress: boolean=false;
  constructor(private fb:FormBuilder,
    private activatedRoute:ActivatedRoute,
    private studentService:StudentService
  ){}
  studentCode!:string
  paymentTypes:string []=[]
  pdfFileUrl!:string
  ngOnInit(): void {
    for(let elts in PaymentType){
      let value=PaymentType[elts]
      if(typeof value=='string'){
        this.paymentTypes.push(PaymentType[elts])

      }
    }
    this.studentCode=this.activatedRoute.snapshot.params['code']
    this.paymentFormGroup=this.fb.group({
      date: this.fb.control(''),
      amount:this.fb.control(''),
      type:this.fb.control(''),
      studentCode:this.fb.control(this.studentCode),
      fileSource:this.fb.control(''),
      fileName:this.fb.control('')


    })
  }

  selectFile(event: any) {
   if(event.target.files.length>0){
    let file=event.target.files[0];
    this.paymentFormGroup.patchValue({
      fileSource:file,
      fileName:file.name
    });
    this.pdfFileUrl=window.URL.createObjectURL(file)
   }
    }
    savePayment() {
      let date:Date=new Date(this.paymentFormGroup.value.date)
      let formattedDate:string=date.getDate()+"/"+(date.getMonth()+1)+'/'+date.getFullYear();
      this.showProgress=true;
      const formData:FormData=new FormData();
      console.log(formattedDate);
      formData.append('file',this.paymentFormGroup.get('fileSource')!.value)
      formData.append('amount',this.paymentFormGroup.value.amount)
      formData.append('type',this.paymentFormGroup.value.type)
      formData.append('date',formattedDate)
      formData.append('studentCode',this.paymentFormGroup.value.studentCode)
this.studentService.savePayment(formData)
.subscribe({
  next:value=>{
    alert("saved succefully")
  },
  error(err) {
      console.error(err);
      
  },
})

      
      

      }

}
