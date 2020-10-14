import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-list-employee',
  templateUrl: './list-employee.component.html',
  styleUrls: ['./list-employee.component.css']
})
export class ListEmployeeComponent implements OnInit {

  barbers: Array<any>;

  constructor(private employeeService: UserService) { }

  ngOnInit(): void {
    this.employeeService.getListEmployee().subscribe(data =>{
      this.barbers = data;
    })
  }

}
