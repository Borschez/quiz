package ru.borsch.quizserver.service;

import ru.borsch.quizserver.model.Answer;

import java.util.Set;

public interface AnswerService {
    Answer findById(Long id);

    Set<Answer> findByQuestionId(Long questionId);
}
