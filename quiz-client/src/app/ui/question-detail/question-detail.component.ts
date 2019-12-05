import {Component, OnInit, Input} from '@angular/core';
import {Location} from '@angular/common';
import {Question} from "../../model/question";
import {ActivatedRoute} from '@angular/router';
import {QuestionService} from "../../services/question.service";
import {Answer} from "../../model/answer";

@Component({
  selector: 'app-question-detail',
  templateUrl: './question-detail.component.html',
  styleUrls: ['./question-detail.component.css']
})
export class QuestionDetailComponent implements OnInit {
  @Input() question: Question

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private questionService: QuestionService) {
  }

  ngOnInit() {
    this.getQuestion();
  }

  goBack(): void {
    this.location.back();
  }

  isCorrectAnswer(answer: Answer): boolean {
    return this.question.correctAnswers.some(function (itemAnswer){
      return itemAnswer.id == answer.id;
    })
  }

  markCorrect(answer: Answer): void {
    this.question.correctAnswers.push(answer);
  }

  markIncorrect(answer: Answer): void {
    let me = this;
    this.question.correctAnswers.some(function (itemAnswer, itemIndex){
      if (itemAnswer.id == answer.id) {
        me.question.correctAnswers.splice(itemIndex, 1);
        return true;
      }
      return false;
    })
  }

  addAnswer(): void {
    let newAnswer = new Answer();
    newAnswer.description = "New Answer";
    this.question.answerOptions.push(newAnswer);
  }

  deleteAnswer(answer: Answer) {
    let answerIndex = this.question.answerOptions.indexOf(answer);
    if (answerIndex > -1) {
      this.question.answerOptions.splice(answerIndex, 1);
    }
  }

  save(): void {
    console.log("save");
    if (this.question.id) {
      this.questionService.updateQuestion(this.question).subscribe(() => this.goBack())
    } else {
      this.questionService.addQuestion(this.question).subscribe(() => this.goBack());
    }
  }

  getQuestion(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    if (id == 0) {
      this.question = new Question();
      this.question.description = "New Question"
      this.question.answerOptions = [];
    }
    if (id > 0) {
      this.questionService.getQuestion(id)
        .subscribe(question => {
          this.question = question;
          this.question.answerOptions.sort((a, b) => { return a.id - b.id});
        });
    }
  }
}
