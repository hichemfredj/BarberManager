import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { RegisterForm } from '../models/register-form';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private httpClient : HttpClient) { }

  register(registerForm : RegisterForm){
    return this.httpClient.post(environment.signupUrl, registerForm).subscribe();
  }
}
