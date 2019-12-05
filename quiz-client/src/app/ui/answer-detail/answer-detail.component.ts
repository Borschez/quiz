import { Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import {Answer} from "../../model/answer";
import {QuestionService} from "../../services/question.service";

@Component({
  selector: 'app-answer-detail',
  templateUrl: './answer-detail.component.html',
  styleUrls: ['./answer-detail.component.css']
})
export class AnswerDetailComponent implements OnInit {
  @Input() answer: Answer
  @Output() deleteAnswerFunc: EventEmitter<any> = new EventEmitter();
  @Output() markCorrect: EventEmitter<any> = new EventEmitter();
  @Output() markIncorrect: EventEmitter<any> = new EventEmitter();
  @Input() isCorrect: boolean;

  constructor(
    private questionService: QuestionService
  ) { }

  ngOnInit() {
  }
}
