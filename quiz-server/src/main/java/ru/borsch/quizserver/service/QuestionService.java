package ru.borsch.quizserver.service;

import ru.borsch.quizserver.model.Answer;
import ru.borsch.quizserver.model.Question;

import java.util.List;
import java.util.Set;

public interface QuestionService {
    Question saveQuestion(Question question);

    Question findById(Long id);
    List<Question> findAll();
    Question findByAnswer(Answer answer);

    List<Question> findBySubjectId(Long subjectId);

}
