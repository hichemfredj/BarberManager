import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { RegisterForm } from '../models/register-form';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient : HttpClient) { }

    headers(){
    let token = localStorage.getItem("token");

    return {
        headers: {
            'Content-Type': 'application/json',
            'Authorization': token
        }
    }
  }


  getListEmployee(): Observable<any>{

    return this.httpClient.get(environment.listEmployeeUrl);

  }

  getUserById(id:string): Observable<any>{

    return this.httpClient.get('http://localhost:9090/user/users/'+id);
  }

}
