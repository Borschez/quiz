import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {AuthService} from "../../services/auth.service";

import {Question} from "../../model/question";
import {QuestionService} from "../../services/question.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  title = 'Home';
  questions: Question[];

  constructor(private http: HttpClient,
              private auth:AuthService,
              private questionService: QuestionService) {
  }

  ngOnInit() {
    this.questionService.getQuestions().subscribe(questions => this.questions = questions);
  }
}
