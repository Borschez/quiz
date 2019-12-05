import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private restAPIUrl = "/api"

  authenticated = false;
  currentUserName = null;

  constructor(private http: HttpClient, private router: Router) { }

  authenticate(credentials, callback) {
    const loginUrl = `${this.restAPIUrl}/user`;
    const headers = new HttpHeaders(credentials ? {
      authorization : 'Basic ' + btoa(credentials.username + ':' + credentials.password)
    } : {});

    this.http.get(loginUrl, {headers: headers}).subscribe(response => {
      if (response['name']) {
        this.authenticated = true;
        this.currentUserName = response['name'];
      } else {
        this.authenticated = false;
        this.currentUserName = null;
      }
      return callback && callback();
    });
  }

  logout() {
    const logoutUrl = `${this.restAPIUrl}/logout`;
    this.http.post(logoutUrl, {}).subscribe(() => {
      this.authenticated = false;
      this.router.navigateByUrl('/login');
    });
  }
}
