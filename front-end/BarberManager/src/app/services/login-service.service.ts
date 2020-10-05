import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map} from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { LoginComponent } from '../components/login/login.component';

import { LoginForm } from '../models/login-form';

@Injectable({
  providedIn: 'root'
})
export class LoginServiceService {


  constructor(private http: HttpClient) { }

  isAuth = false;

  login(loginForm : LoginForm){
    return this.http.post(environment.loginUrl, loginForm);
  }

  logout(){
    localStorage.setItem('UserUniqueId', '');
    localStorage.setItem('UserType', '');
    localStorage.setItem('token', '');

    this.isAuth = false;
  }

  isAuthenticated(){
    return this.isAuth;
  }

  
}
