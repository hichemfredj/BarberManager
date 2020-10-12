import { Component, OnInit } from '@angular/core';

import { FormGroup, FormArray, FormBuilder, Validators, FormControl } from '@angular/forms';
import { AvailabilityForm } from 'src/app/models/availability-form';



@Component({
  selector: 'app-availability',
  templateUrl: './availability.component.html',
  styleUrls: ['./availability.component.css']
})
export class AvailabilityComponent implements OnInit {

  days = ['Lundi', 'Mardi', 'Mercredi', 'Jeudi', 'Vendredi', 'Samedi', 'Dimanche'];

  availabilityForm : FormArray;

  availabilityFormGroup : FormGroup;


  constructor(private formBuilder: FormBuilder) { }


  
  
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
      startTime:[''],
      endTime:[''],
      isAvailable:[false]
    });
  }

  
 

}
