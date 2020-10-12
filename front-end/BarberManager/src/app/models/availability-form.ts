import { WeekDay } from '@angular/common'

export interface AvailabilityForm {

    day : string;
    startTime : string;
    endTime : string;
    isAvailable : boolean;

}