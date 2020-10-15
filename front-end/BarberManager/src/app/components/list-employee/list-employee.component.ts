import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import {MatDialog} from '@angular/material/dialog';
import { ReservationComponent } from '../reservation/reservation.component';

@Component({
  selector: 'app-list-employee',
  templateUrl: './list-employee.component.html',
  styleUrls: ['./list-employee.component.css']
})
export class ListEmployeeComponent implements OnInit {

  barbers: Array<any>;

  constructor(private employeeService: UserService, public dialog: MatDialog) { }

  openDialog(barber: any){
    const dialogRef = this.dialog.open(ReservationComponent, {data : barber});
    dialogRef.afterClosed().subscribe(result=>{

      console.log(`Dialog result: ${result}`);
    })
  }

  ngOnInit(): void {
    this.employeeService.getListEmployee().subscribe(data =>{
      this.barbers = data;
      console.log(this.barbers);
    })
  }

}

