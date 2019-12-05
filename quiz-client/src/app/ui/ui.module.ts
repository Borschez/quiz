import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Injectable } from '@angular/core';
import {HttpInterceptor, HttpRequest, HttpHandler, HTTP_INTERCEPTORS} from '@angular/common/http';

import { LayoutComponent } from './layout/layout.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { AppRoutingModule} from "../app-routing.module";
import { HttpClientModule} from '@angular/common/http';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { FormsModule } from '@angular/forms';
import { QuestionDetailComponent } from './question-detail/question-detail.component';
import { AnswerDetailComponent } from './answer-detail/answer-detail.component';

@Injectable()
export class XhrInterceptor implements HttpInterceptor {

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    const xhr = req.clone({
      headers: req.headers.set('X-Requested-With', 'XMLHttpRequest')
    });
    return next.handle(xhr);
  }
}

@NgModule({
  declarations: [LayoutComponent, HeaderComponent, FooterComponent, UserProfileComponent, HomeComponent, LoginComponent, QuestionDetailComponent, AnswerDetailComponent],
  imports: [
    CommonModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: XhrInterceptor, multi: true }],
  exports: [LayoutComponent]
})
export class UiModule { }
