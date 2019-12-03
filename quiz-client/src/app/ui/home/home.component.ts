import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {AuthService} from "../../services/auth.service";


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  title = 'Demo';
  greeting = {};

  constructor(private http: HttpClient, private auth:AuthService) {
    http.get('/api/resource').subscribe(data => this.greeting = data);
  }

  ngOnInit() {
  }

  authenticated() { return this.auth.authenticated; }

}
