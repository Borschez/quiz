import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {UserProfileComponent} from "./ui/user-profile/user-profile.component";
import {HomeComponent} from "./ui/home/home.component";
import {LoginComponent} from "./ui/login/login.component";
import {QuestionDetailComponent} from "./ui/question-detail/question-detail.component";


const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'userProfile', component: UserProfileComponent },
  { path: 'home', component: HomeComponent},
  { path: 'login', component: LoginComponent},
  { path: 'add-question', component: QuestionDetailComponent},
  { path: 'question/:id', component: QuestionDetailComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
