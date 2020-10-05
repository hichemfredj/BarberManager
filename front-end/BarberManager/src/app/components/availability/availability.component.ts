import { Component, OnInit } from '@angular/core';

import { FormGroup, FormArray, FormBuilder, Validators } from '@angular/forms';


@Component({
  selector: 'app-availability',
  templateUrl: './availability.component.html',
  styleUrls: ['./availability.component.css']
})
export class AvailabilityComponent implements OnInit {

  constructor(private _fb: FormBuilder) { }

  public addmore: FormGroup;

  ngOnInit() {
    this.addmore = this._fb.group({
      dayName:[''],
  	  startdate:[''],
  	  endDate:[''],
      itemRows: this._fb.array([this.initItemRows()])
    });
  }
  get formArr() {
    return this.addmore.get('itemRows') as FormArray;
  }

  initItemRows() {
    return this._fb.group({
    timeRange:['']
    });
  }
  addNewRow() {
    this.formArr.push(this.initItemRows());
  }

  deleteRow(index: number) {
    this.formArr.removeAt(index);
  }

}
