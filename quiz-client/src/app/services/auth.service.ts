import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private restAPIUrl = "/api"

  authenticated = false;

  constructor(private http: HttpClient, private router: Router) { }

  authenticate(credentials, callback) {
    const loginUrl = `${this.restAPIUrl}/user`;
    const headers = new HttpHeaders(credentials ? {
      authorization : 'Basic ' + btoa(credentials.username + ':' + credentials.password)
    } : {});

    console.log(headers);

    this.http.get(loginUrl, {headers: headers}).subscribe(response => {
      if (response['name']) {
        this.authenticated = true;
      } else {
        this.authenticated = false;
      }
      return callback && callback();
    });
  }

  /*logout() {
    this.http.post('logout', {}).finally(() => {
      this.authenticated = false;
      this.router.navigateByUrl('/login');
    }).subscribe();
  }*/
}
