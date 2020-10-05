import { Component, OnInit, EventEmitter, Output, ChangeDetectorRef, OnDestroy } from '@angular/core';
import { LoginServiceService } from './services/login-service.service';
import {MediaMatcher} from '@angular/cdk/layout';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy{

  mobileQuery: MediaQueryList;
  fillerNav = Array.from({length: 50}, (_, i) => `Nav Item ${i + 1}`);
  title = 'BarberManager';
  role : string;
  isAuth = false;

  @Output() public sidenavClose = new EventEmitter ();



  private _mobileQueryListener: () => void;

  constructor(private loginService: LoginServiceService, changeDetectorRef: ChangeDetectorRef, media: MediaMatcher){
    this.mobileQuery = media.matchMedia('(max-width: 600px)');
    this._mobileQueryListener = () => changeDetectorRef.detectChanges();
    this.mobileQuery.addListener(this._mobileQueryListener);
  }

  ngOnDestroy(): void {
    this.mobileQuery.removeListener(this._mobileQueryListener);
  }


  ngOnInit(){
    this.role = localStorage.getItem('UserType');
    console.log(this.role);

    console.log(this.isAuth);
  }

  public onSidenavClose = () => {
    this.sidenavClose.emit();
  }

  logoutClicked(){
    console.log("lougout clicked");
    this.loginService.logout();
    this.role = localStorage.getItem('UserType');

  }

  getRole(){
    return this.role;
  }

  setRole(role){
    this.role = role;
  }


}
