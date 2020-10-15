import { Component, Inject, OnInit } from '@angular/core';
import {MatDatepickerInputEvent} from '@angular/material/datepicker';

import { FormGroup, FormArray, FormBuilder, Validators, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { ReservationService } from 'src/app/services/reservation.service';
import * as moment from 'moment';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ReservationForm } from 'src/app/models/reservation-form';

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
})
export class ReservationComponent implements OnInit {

  time = ['11:00', '11:30', '12:00', '12:30', '13:00', '13:30', '14:00', '14:30', '15:00', '15:30', '16:00', '16:30', '17:00'];
  timeSelected : string;
  finalTime: string;
  finalDate: string;
  reservationForm: FormGroup;
  date:string;
  clientId: any;
  
  events: string[] = [];

  // addEvent(type: string, event: MatDatepickerInputEvent<Date>) {
  //   this.events.push(`${type}: ${event.value}`);
  //   console.log(this.events);
  // }
  
  constructor(@Inject(MAT_DIALOG_DATA) public data: any, private formBuilder: FormBuilder, private route: Router, private reservationService : ReservationService) { }
  
  public onDate(event): void {
    this.date = event;
    console.log(this.date);
  }

  getDate(){
    return this.date;
  }


  ngOnInit(): void {

    this.initForm();
    this.clientId = localStorage.getItem('UserUniqueId');
    console.log(this.data.uniqueId);
    

  }

  initForm(){
    console.log(this.time)
  }

  onSubmitForm(){
    const reservationForm : ReservationForm = {
      date : this.finalDate,
      time: this.finalTime,
      barberName: this.data.firstName
    }

    this.reservationService.createReservation(reservationForm).subscribe(()=>{

    },error=>{
        console.log(error);
    })
  }

  onConfirm(){

    this.finalTime= this.timeSelected;
    this.finalDate = this.getDate();

    console.log(this.finalTime);

  }

}
