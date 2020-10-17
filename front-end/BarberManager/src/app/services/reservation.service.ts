import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
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

  getListReservation(): Observable<any>{
    return this.httpClient.get(environment.listReservationUrl);
  }

  getListReservationByBarberId(barberId:string){
    return this.httpClient.get('http://localhost:9090/reservation/list-reservation/barber/'+barberId);
  }
  getListReservationByClientId(clientId:string){
    return this.httpClient.get('http://localhost:9090/reservation/list-reservation/client/'+clientId);
  }
}
