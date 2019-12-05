import {Answer} from "./answer";

export class Question {
  id: number;
  description: string;
  answerOptions: Answer[];
  correctAnswers: Answer[];
}
