import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { RegisterForm } from 'src/app/models/register-form';
import { RegisterService } from 'src/app/services/register.service';
import { MustMatch } from '../utility/must-match-validator';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  signupForm : FormGroup;
  emailAlreadyExist = false;
  isSubmitted = false;

  constructor(private formBuilder : FormBuilder, private route : Router, private registerService : RegisterService) { }

  ngOnInit(): void {

    this.initForm();
  }

  initForm(){
    this.signupForm = this.formBuilder.group({
      firstName :['', [Validators.required]],
      lastName :['', [Validators.required]],
      email : ['', [Validators.required, Validators.email]],
      password : [null, Validators.compose([
        Validators.required,
        Validators.minLength(6),
        Validators.maxLength(18)
      ])],
      confirmPass : ['', Validators.required]
    },{
      validator : MustMatch('password','confirmPass')
    })
  }

  onSubmitForm(){

    this.isSubmitted = true;

    if (this.signupForm.valid) {

      const formValue = this.signupForm.value;
  
      const signupForm : RegisterForm = {
        firstName: formValue['firstName'],
        lastName: formValue['lastName'],
        email: formValue['email'],
        password: formValue['password']
      }

      this.registerService.register(signupForm).subscribe(()=>{
        
      },error=>{
                
          if(error.error.errors[0].defaultMessage){
            this.emailAlreadyExist = true;
            console.log(this.emailAlreadyExist); 
          }
      });
      console.log(signupForm);
    }
  }
  
  getF(){
    return this.signupForm.controls;
  }

}
