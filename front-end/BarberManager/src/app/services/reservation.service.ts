import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

import {ReservationForm} from '../models/reservation-form';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  constructor(private httpClient : HttpClient) { }

  createReservation(reservationDTO: ReservationForm){
    return this.httpClient.post(environment.createReservationUrl,reservationDTO);
  }
}
