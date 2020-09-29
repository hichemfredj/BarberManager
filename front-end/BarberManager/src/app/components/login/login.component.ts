import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginForm } from 'src/app/models/login-form';
import { LoginServiceService } from 'src/app/services/login-service.service';
import { JwtResponse } from 'src/app/models/jwt-response'


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  
  loginForm: FormGroup;

  isSubmitted = false;


  constructor(private formBuilder: FormBuilder, private router: Router, private loginService: LoginServiceService) { }

  authenticated = true;

  ngOnInit(): void {

    this.initForm();
    

  }

  initForm(){
    this.loginForm = this.formBuilder.group({
      email : ['', [Validators.required, Validators.email]],
      password : ['', Validators.required]
    })
  }

  onSubmitForm(){

    this.isSubmitted = true;

    if (this.loginForm.valid) {

      const formValue = this.loginForm.value;
  
      const loginForm : LoginForm = {
        email: formValue['email'],
        password: formValue['password']
      }

      this.loginService.login(loginForm).subscribe((data:JwtResponse) =>{
        if(data && data.token && data.userUniqueId){
          localStorage.setItem('token',data.token);
          localStorage.setItem('UserUniqueId',data.userUniqueId);
          this.authenticated = true;
        }
      },error=>{
  
          this.authenticated = false;
      });

      console.log(loginForm);
      
    }
  }

}
