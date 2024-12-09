import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { StudentService } from '../services/student.service';
import { Payment } from '../model/students.model';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-student-details',
  templateUrl: './student-details.component.html',
  styleUrl: './student-details.component.css'
})
export class StudentDetailsComponent implements OnInit {

filterStudents($event: Event) {
throw new Error('Method not implemented.');
}
  constructor(private activatedRoute:ActivatedRoute,
    private studentService:StudentService,
    private router:Router
  ){}
  studentCode!:string
  studentPayments!:Array<Payment>
  PyamentDataSource!: MatTableDataSource<Payment>
  public displayedColumns = ['id','date','type','status','amount','firstName'];
    ngOnInit(): void {
   this.studentCode=this.activatedRoute.snapshot.params['code']
   this.studentService.getStudentPayments(this.studentCode)
   .subscribe({
    next:value =>{
      this.studentPayments=value
      this.PyamentDataSource=new MatTableDataSource<Payment>(this.studentPayments)
    },
    error:err=>{
      console.error(err);
      
    }
   })
  }
  newPayment() {
this.router.navigateByUrl(`/admin/new-payment/${this.studentCode}`)   
 }

}
