import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AvailabilityComponent } from './components/availability/availability.component';
import { DashbordComponent } from './components/dashbord/dashbord.component';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';

const routes: Routes = [
  {path:'login', component : LoginComponent},
  {path:'signup', component : SignupComponent},
  {path:'dashbord', component: DashbordComponent},
  {path:'availability', component: AvailabilityComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
