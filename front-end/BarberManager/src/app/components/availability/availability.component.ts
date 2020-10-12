import { Component, OnInit } from '@angular/core';

import { FormGroup, FormArray, FormBuilder, Validators, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { AvailabilityForm } from 'src/app/models/availability-form';
import { JwtResponse } from 'src/app/models/jwt-response';
import { AvailabilityService } from 'src/app/services/availability.service';



@Component({
  selector: 'app-availability',
  templateUrl: './availability.component.html',
  styleUrls: ['./availability.component.css']
})
export class AvailabilityComponent implements OnInit {

  days = ['Lundi', 'Mardi', 'Mercredi', 'Jeudi', 'Vendredi', 'Samedi', 'Dimanche'];

  availabilityForm : FormArray;

  availabilityFormGroup : FormGroup;


  constructor(private formBuilder: FormBuilder, private route : Router, private availabilityService : AvailabilityService) { }


  
  
  ngOnInit() {
    this.initForm();
    console.log(this.getFormArr());

  }

  initForm(){
    this.availabilityFormGroup = this.formBuilder.group({
      availabilityForm : new FormArray([])
    })
    this.days.forEach((day) =>{
      this.getFormArr().push(this.initItemRow(day));
    });
    
  }

  getFormArr(){
    return this.availabilityFormGroup.get('availabilityForm') as FormArray;
  }

  initItemRow(day : string){
    return this.formBuilder.group({
      day:[day],
      startTime:['',[Validators.required]],
      endTime:['',[Validators.required]],
      isAvailable:['false',[Validators.required]]
    });
  }

  submit(){
    console.log(this.getFormArr().value);
    this.onSubmitForm();
  }

  onSubmitForm(){

    console.log("allllloooooo");

    // if(this.availabilityForm.valid){

     // console.log(this.getFormArr().at(0).value);

      const availabilityForm : Array<AvailabilityForm> =[];

      for(var i = 0 ; i < this.getFormArr().length; i++ ){
        availabilityForm.push(this.getFormArr().at(i).value);

        //console.log(availabilityForm[i]);
      }

    this.availabilityService.createAvailability(availabilityForm).subscribe(()=>{
    },error=>{
      console.log(error);
    })
    
  }

  
 

}
