import { Component, OnInit } from '@angular/core';
import { ReservationForm } from 'src/app/models/reservation-form';
import { ReservationService } from 'src/app/services/reservation.service';

@Component({
  selector: 'app-list-reservation',
  templateUrl: './list-reservation.component.html',
  styleUrls: ['./list-reservation.component.css']
})
export class ListReservationComponent implements OnInit {

  listReservation : any;
  columnsToDisplay = ['clientName', 'barberName', 'date', 'time'];

  userType : string;
  userId : string;

  constructor(private reservationService: ReservationService) { }

  ngOnInit(): void {

    this.userType = localStorage.getItem("UserType");
    

    if(this.isEmployer()){
      this.userId = localStorage.getItem("UserUniqueId");
      this.reservationService.getListReservationByBarberId(this.userId).subscribe(data=>{
        this.listReservation = data;
        console.log(this.listReservation)
      })
    }else if(this.isClient()){
      this.userId = localStorage.getItem("UserUniqueId");
      this.reservationService.getListReservationByClientId(this.userId).subscribe(data=>{
        this.listReservation = data;
        console.log(this.listReservation)
      })
    }
    
  }

  isClient() : boolean{
    return this.userType == "CLIENT"
  }

  isEmployer() : boolean{
    return this.userType == "EMPLOYER"
  }

}
