import { HttpClient } from '@angular/common/http';
import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { StudentService } from '../services/student.service';

@Component({
  selector: 'app-payments',
  templateUrl: './payments.component.html',
  styleUrl: './payments.component.css'
})
export class PaymentsComponent implements OnInit, AfterViewInit{
  public payments : any;
  public displayedColumns = ['id','date','type','status','amount','firstName'];
  public dataSource : any;

  @ViewChild(MatPaginator) paginator! : MatPaginator;
  @ViewChild(MatSort) sort! : MatSort;
 constructor(
  private studentService:StudentService
 ) {
 }
  ngAfterViewInit(): void {
    throw new Error('Method not implemented.');
  }
 ngOnInit() {
  this.studentService.getAllPayments()
  .subscribe({
    next:data =>{
      this.payments = data;
      this.dataSource = new MatTableDataSource(this.payments);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;

    },
    error:err=>{
      console.error(err);
      

    }
  })
 }
}
