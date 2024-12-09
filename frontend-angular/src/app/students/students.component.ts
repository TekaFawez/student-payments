import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { StudentService } from '../services/student.service';
import { Student } from '../model/students.model';

@Component({
  selector: 'app-students',
  templateUrl: './students.component.html',
  styleUrl: './students.component.css'
})
export class StudentsComponent implements OnInit,AfterViewInit {


  public students!:Student[]
  public displayedColumns =["id","firstName","lastName","code","payments","programId"] 
   public dataSource:any
  @ViewChild(MatPaginator) paginator! :MatPaginator
  @ViewChild(MatSort) sort!:MatSort

  constructor(private router:Router,
    private service:StudentService
  ){}
  ngAfterViewInit(): void {
    this.dataSource.paginator=this.paginator
    this.dataSource.sort=this.sort;

  }
  ngOnInit(): void {
    this.service.getAllStudents()
    .subscribe({
      next:data=>{
        this.students=data;
        this.dataSource=new MatTableDataSource(this.students)
        this.dataSource.paginator=this.paginator
        this.dataSource.sort=this.sort;



      },
      error:err=>{
        console.error(err);
        
      }
    })


  }
  filterStudents(event: Event) {
    let value = (event.target as HTMLInputElement).value;
    this.dataSource.filter = value;
    }


    getPayments(student : Student) {
      this.router.navigateByUrl(`/admin/student-details/${student.code}`)
    }
}
