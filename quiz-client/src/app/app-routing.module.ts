import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {UserProfileComponent} from "./ui/user-profile/user-profile.component";
import {HomeComponent} from "./ui/home/home.component";
import {LoginComponent} from "./ui/login/login.component";


const routes: Routes = [
  { path: 'userProfile', component: UserProfileComponent },
  { path: 'home', component: HomeComponent},
  { path: 'login', component: LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
