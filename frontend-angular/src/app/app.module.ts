import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {MatToolbar, MatToolbarModule} from "@angular/material/toolbar";
import {MatButton, MatButtonModule} from "@angular/material/button";
import {MatIcon, MatIconModule} from "@angular/material/icon";
import {MatMenu, MatMenuItem, MatMenuModule, MatMenuTrigger} from "@angular/material/menu";
import {MatDrawerContainer, MatSidenavModule} from "@angular/material/sidenav";
import {MatListItem, MatNavList} from "@angular/material/list";import {MatCard, MatCardHeader, MatCardModule} from "@angular/material/card";
import {MatDivider, MatDividerModule} from "@angular/material/divider";
import {MatTable, MatTableModule} from "@angular/material/table";
import {MatSort, MatSortHeader} from "@angular/material/sort";
import {MatFormField, MatInput} from "@angular/material/input";
import {MatFormFieldModule} from "@angular/material/form-field";
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { AdminTemplateComponent } from './admin-template/admin-template.component';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { LoginComponent } from './login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { StudentsComponent } from './students/students.component';
import { PaymentsComponent } from './payments/payments.component';
import { LoadStudentsComponent } from './load-students/load-students.component';
import { LoadPaymentsComponent } from './load-payments/load-payments.component';
import { MatPaginator } from '@angular/material/paginator';
import { ReactiveFormsModule } from '@angular/forms';
import { AuthGuard } from './guards/auth.guard';
import { AuthorizationGuard } from './guards/authorization.guard';
import { HttpClientModule } from '@angular/common/http';
import { StudentDetailsComponent } from './student-details/student-details.component';
import { NewPaymentComponent } from './new-payment/new-payment.component';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import {MatSelectModule} from '@angular/material/select';
import { PdfViewerModule } from 'ng2-pdf-viewer';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';

@NgModule({
  declarations: [
    AppComponent,
    AdminTemplateComponent,
    HomeComponent,
    ProfileComponent,
    LoginComponent,
    DashboardComponent,
    StudentsComponent,
    PaymentsComponent,
    LoadStudentsComponent,
    LoadPaymentsComponent,
    StudentDetailsComponent,
    NewPaymentComponent,
     
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    MatMenuModule,
    MatSidenavModule,
    MatListItem,
    MatNavList,
    MatCardModule,
    MatDividerModule,
    MatTableModule,
    MatPaginator,
    MatSortHeader,
    MatSort,
    MatInput,
    MatFormFieldModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatSelectModule,
    PdfViewerModule,
    MatProgressSpinnerModule
  ],
  providers: [
    provideAnimationsAsync(),
    AuthGuard,AuthorizationGuard
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
