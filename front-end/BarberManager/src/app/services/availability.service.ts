import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

import {AvailabilityForm} from '../models/availability-form';

@Injectable({
  providedIn: 'root'
})
export class AvailabilityService {

  constructor(private http: HttpClient) { }

  createAvailability(availabilityForm: AvailabilityForm){
    return this.http.post(environment.availabilityUrl, availabilityForm);
  }
}
