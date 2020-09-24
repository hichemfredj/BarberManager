import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginForm } from 'src/app/models/login-form';
import { LoginServiceService } from 'src/app/services/login-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  
  loginForm: FormGroup;

  constructor(private formBuilder: FormBuilder, private router: Router, private loginService: LoginServiceService) { }

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
    if (this.loginForm.valid) {

      const formValue = this.loginForm.value;
  
      const loginForm : LoginForm = {
        email: formValue['email'],
        password: formValue['password']
      }

      this.loginService.login(loginForm);

      console.log(loginForm);
      
    }
  }

}
