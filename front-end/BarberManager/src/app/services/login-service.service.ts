import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map} from 'rxjs/operators';
import { environment } from 'src/environments/environment';

import { LoginForm } from '../models/login-form';

@Injectable({
  providedIn: 'root'
})
export class LoginServiceService {


  constructor(private http: HttpClient) { }

  login(loginForm : LoginForm){
    return this.http.post(environment.loginUrl, loginForm);
  }

  
}
