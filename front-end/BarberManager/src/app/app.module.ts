import { BrowserModule } from '@angular/platform-browser';
import { NgModule, LOCALE_ID } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { JwtModule } from '@auth0/angular-jwt';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatToolbarModule } from  '@angular/material/toolbar';
import { MatIconModule} from  '@angular/material/icon';
import { MatSidenavModule} from  '@angular/material/sidenav';
import { MatListModule} from  '@angular/material/list';
import { MatButtonModule } from  '@angular/material/button';
import { MatFormFieldModule } from  '@angular/material/form-field';
import { MatInputModule } from  '@angular/material/input';
import { MatTableModule, } from  '@angular/material/table';
import {MatCardModule} from '@angular/material/card';
import {MatDialogModule} from '@angular/material/dialog';
import {MatDatepickerModule} from '@angular/material/datepicker'
import { AvailabilityComponent } from './components/availability/availability.component';
import {MatNativeDateModule, MatOptionModule} from '@angular/material/core';
import {MAT_FORM_FIELD_DEFAULT_OPTIONS} from '@angular/material/form-field';
import { ListEmployeeComponent } from './components/list-employee/list-employee.component';
import { ReservationComponent } from './components/reservation/reservation.component';
import { MatSelectModule } from '@angular/material/select';
import { ListReservationComponent } from './components/list-reservation/list-reservation.component';
import {DatePipe} from '@angular/common';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent,
    AvailabilityComponent,
    ListEmployeeComponent,
    ReservationComponent,
    ListReservationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatToolbarModule,
    MatSidenavModule,
    MatListModule,
    MatButtonModule,
    MatIconModule,
    MatFormFieldModule,
    MatInputModule,
    MatTableModule,
    MatNativeDateModule,
    MatOptionModule,
    MatCardModule,
    MatDialogModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatSelectModule,
    ReactiveFormsModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: () => {
          return localStorage.getItem("token");
        },
        allowedDomains: ['localhost:9090'],
        disallowedRoutes: ['localhost:9090/authenticate','localhost:9090/registration/client'],
      },
    }),
    BrowserAnimationsModule
  ],
  providers: [
    DatePipe,
    { provide: MAT_FORM_FIELD_DEFAULT_OPTIONS, useValue: { appearance: 'fill' } },
    {provide : LOCALE_ID, useValue: 'en-CA' }
  ],
  bootstrap: [AppComponent,AvailabilityComponent]
})
export class AppModule { }
