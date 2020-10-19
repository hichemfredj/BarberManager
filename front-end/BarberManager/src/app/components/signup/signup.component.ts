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
  emailAlreadyExist;
  isSubmitted = false;
  isStudent = false;
  isEmployer = false;
  colorStudent = "";
  colorEmployer = "";


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

      if(this.isStudent){
        this.registerService.register(signupForm).subscribe(()=>{
          this.route.navigate(['/login']);
        },error=>{
            console.log(error.error.errors[0].defaultMessage);
            if(error.error.errors[0].defaultMessage){
              this.emailAlreadyExist = true;
              console.log(this.emailAlreadyExist);
            }
        });
        
      }
      if(this.isEmployer){
        this.registerService.registerEmployer(signupForm).subscribe(()=>{
          this.route.navigate(['/login']);
        },error=>{
            
            if(error.error.errors[0].defaultMessage){
              this.emailAlreadyExist = true;
            }
        });
      }
      console.log(signupForm);
    }
  }
  
  getF(){
    return this.signupForm.controls;
  }

  isEmployerClicked(){
    
    this.isStudent = false;
    this.isEmployer = true;
    this.colorStudent = "";
    this.colorEmployer = "primary";
    console.log("employer : "+this.isEmployer + "," + "student : "+this.isStudent+";")
  }

  isStudentClicked(){
    this.isEmployer = false;
    this.isStudent = true;
    this.colorStudent = "primary";
    this.colorEmployer = "";
    console.log("employer : "+this.isEmployer + "," + "student : "+this.isStudent+";")
  }



}
