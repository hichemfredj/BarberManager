import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { JwtModule } from '@auth0/angular-jwt';
import { environment } from 'src/environments/environment';
import { DashbordComponent } from './components/dashbord/dashbord.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatToolbarModule } from  '@angular/material/toolbar';
import { MatIconModule} from  '@angular/material/icon';
import { MatSidenavModule} from  '@angular/material/sidenav';
import { MatListModule} from  '@angular/material/list';
import { MatButtonModule } from  '@angular/material/button';
import { MatFormFieldModule } from  '@angular/material/form-field';
import { MatInputModule } from  '@angular/material/input';
import { MatTableModule, } from  '@angular/material/table';
import { AvailabilityComponent } from './components/availability/availability.component';
import {MatNativeDateModule, MatOptionModule} from '@angular/material/core';
import {MAT_FORM_FIELD_DEFAULT_OPTIONS} from '@angular/material/form-field';



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent,
    DashbordComponent,
    AvailabilityComponent
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
    ReactiveFormsModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: () => {
          return localStorage.getItem("token");
        },
        allowedDomains: [environment.globalUrl],
        disallowedRoutes: [environment.loginUrl,environment.signupUrl],
      },
    }),
    BrowserAnimationsModule
  ],
  providers: [{ provide: MAT_FORM_FIELD_DEFAULT_OPTIONS, useValue: { appearance: 'fill' } },],
  bootstrap: [AppComponent,AvailabilityComponent]
})
export class AppModule { }
