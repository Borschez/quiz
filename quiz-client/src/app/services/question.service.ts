import {Injectable} from '@angular/core';
import {Observable, of} from 'rxjs';
import {catchError, map, tap} from 'rxjs/operators';
import {HttpClient} from '@angular/common/http';
import {Question} from "../model/question";
import {Answer} from "../model/answer";

@Injectable({
  providedIn: 'root'
})
export class QuestionService {
  private questionUrl = '/api/question';
  private questionsUrl = '/api/questions';
  private answerUrl = '/api/answer';

  constructor(
    private http: HttpClient
  ) {
  }

  getQuestions(): Observable<Question[]> {
    return this.http.get<Question[]>(this.questionsUrl).pipe(
      catchError(this.handleError('getQuestions', []))
    );
  }

  addQuestion(question: Question): Observable<Question> {
    return this.http.post<Question>(this.questionUrl, question).pipe(
      catchError(this.handleError<Question>('addQuestion'))
    );
  }

  updateQuestion(question: Question): Observable<Question> {
    const id = question.id;
    const url = `${this.questionUrl}/${id}`;
    return this.http.put<Question>(url, question).pipe(
      catchError(this.handleError<Question>('updateQuestion'))
    );
  }

  getQuestion(id: number): Observable<Question> {
    const url = `${this.questionUrl}/${id}`;
    return this.http.get<Question>(url).pipe(
      catchError(this.handleError<Question>(`getQuestion id=${id}`))
    );
  }

  deleteAnswer(answer: Answer): Observable<Answer> {
    const id = answer.id;
    const url = `${this.answerUrl}/${id}`;
    return this.http.delete<Answer>(url).pipe(
      catchError(this.handleError<Answer>('deleteAnswer'))
    );
  }

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      //this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}
