import { Component, Inject, OnInit } from '@angular/core';
import {MatDatepickerInputEvent} from '@angular/material/datepicker';

import { FormGroup, FormArray, FormBuilder, Validators, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { ReservationService } from 'src/app/services/reservation.service';
import * as moment from 'moment';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ReservationForm } from 'src/app/models/reservation-form';
import { UserService } from 'src/app/services/user.service';
import { RegisterForm } from 'src/app/models/register-form';
import { UserDTO } from 'src/app/models/userDTO';
import { DatePipe } from '@angular/common';
import { Observable } from 'rxjs';
import { AvailabilityService } from 'src/app/services/availability.service';

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
})
export class ReservationComponent implements OnInit {

  time = ['08:00','09:00','10:00','11:00','12:00','13:00','14:00','15:00','16:00','17:00', '18:00', '19:00', '20:00', '21:00', '22:00' ,'23:00'];
  firstArrayTime : any;
  secondArrayTime : any;
  timeToBeDisplayed : any;
  timeSelected : string;
  finalTime: string;
  finalDate: string;
  reservationForm: FormGroup;
  date:string;
  clientId: any;
  user: UserDTO;
  testDate:any;
  listReservationBydate : any
  listReservationTaken: Array<any>;
  currentDate: any;
  timeTaken:Array<any>;
  size:number;
  listName :any;
  hasTimeSelected : boolean;
  weekday = new Array(7);
  currentWeekDay : any;
  disponibilite:any;
  isEmpty : boolean;



  
  constructor(private datePipe: DatePipe,@Inject(MAT_DIALOG_DATA) public data: any, private formBuilder: FormBuilder, private route: Router, private reservationService : ReservationService, private userService : UserService, private availabilityService : AvailabilityService) { }
  
  ngOnInit(): void {

    this.isEmpty = false;

    this.weekday[0] = "Dimanche";
    this.weekday[1] = "Lundi";
    this.weekday[2] = "Mardi";
    this.weekday[3] = "Mercredi";
    this.weekday[4] = "Jeudi";
    this.weekday[5] = "Vendredi";
    this.weekday[6] = "Samedi";


    this.initForm();
    this.clientId = localStorage.getItem('UserUniqueId');
    console.log(this.data.uniqueId);

    this.getUser();

  }

  initForm(){
    console.log(this.time)
  }

  public getListReservationByDate(){
    this.callServiceMethodReservation().subscribe(data=>{
      this.listReservationBydate = data;
      this.listReservationTaken = this.listReservationBydate.map((item)=>{
        return item.time;
      })
      console.log(this.listReservationBydate);
      console.log(this.listReservationTaken);
      console.log(this.listReservationTaken.length);

      this.callServiceMethodDisponiblite().subscribe(data=>{
        this.disponibilite = data;
        console.log(this.disponibilite);
        this.firstArrayTime = this.time.filter(time=>{
          return time >= this.disponibilite.startTime;
        })
        this.secondArrayTime = this.firstArrayTime.filter(time=>{
          return time <= this.disponibilite.endTime;
        })
         console.log(this.secondArrayTime);

         this.timeToBeDisplayed = this.secondArrayTime;
         for(var i = 0; i<this.timeToBeDisplayed.length; i++){
            for(var j = 0; j<this.listReservationTaken.length; j++){
              if (this.timeToBeDisplayed[i] == this.listReservationTaken[j]){
                this.timeToBeDisplayed.splice(i,1);
                i--;
              }
            }
          }
          console.log(this.secondArrayTime);
          console.log(this.timeToBeDisplayed);
          if(this.secondArrayTime.length == 0){
            this.isEmpty = true;
          }else{
            this.isEmpty = false;
          }
          console.log(this.isEmpty);
      })
    })
  }

  checkEmpty() : boolean{
    return this.isEmpty;
  }


  public getDisponibilite(){
    this.callServiceMethodDisponiblite().subscribe(data=>{
      this.disponibilite = data;
      console.log(this.disponibilite);
      this.firstArrayTime = this.time.filter(time=>{
        return time >= this.disponibilite.startTime;
      })
      this.secondArrayTime = this.firstArrayTime.filter(time=>{
        return time <= this.disponibilite.endTime;
      })
       console.log(this.secondArrayTime);
    })
  }

  callServiceMethodDisponiblite() : Observable<any>{
    return this.availabilityService.getAvailability(this.currentWeekDay, this.data.uniqueId);
  }

  callServiceMethodReservation() : Observable<any>{
    return this.reservationService.getListReservationByDateAndByEmployer(this.datePipe.transform(this.currentDate, "yyyy-MM-dd"), this.data.uniqueId);
  }

  public onDate(event): void {
    this.date = event;
    this.currentDate = this.date;
    this.getListReservationByDate();
    this.currentWeekDay = this.weekday[this.currentDate.getDay()];
   //this.getDisponibilite();
  }

  getDate(){
    return this.date;
  }
  
  onSubmitForm(){
    const reservationForm : ReservationForm = {
      client: this.clientId,
      employer : this.data.uniqueId,
      date : this.datePipe.transform(this.finalDate,"yyyy-MM-dd"),
      time: this.finalTime,
      barberName: this.data.firstName,
      clientName: this.user.firstName
    }
    
    // console.log(reservationForm);
    //  this.testDate = new Date(this.datePipe.transform(this.date,"yyyy-MM-dd"));
    //  console.log(this.testDate.getMonth()+1);
    
    
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
      public getUser(){
        this.userService.getUserById(localStorage.getItem('UserUniqueId')).subscribe(data=>{
          this.user = data;
        });
      }

}
