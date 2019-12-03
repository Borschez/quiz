import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
  title = 'Demo';
  userInfo = {};

  constructor(private http: HttpClient) {
    http.get('/api/user-info').subscribe(data => this.userInfo = data);
  }

  ngOnInit() {
  }

}
