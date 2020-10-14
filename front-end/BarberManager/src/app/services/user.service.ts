import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

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

}
