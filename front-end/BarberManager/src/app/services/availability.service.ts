import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

import {AvailabilityForm} from '../models/availability-form';

@Injectable({
  providedIn: 'root'
})
export class AvailabilityService {

  constructor(private http: HttpClient) { }

  headers(){
    let token = localStorage.getItem("token");

    return {
        headers: {
            'Content-Type': 'application/json',
            'Authorization': token
        }
    }
  }

  createAvailability(availability: Array<AvailabilityForm>){
    return this.http.post(environment.availabilityUrl, availability, this.headers());
  }
}
