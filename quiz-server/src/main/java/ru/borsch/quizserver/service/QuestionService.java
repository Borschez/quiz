package ru.borsch.quizserver.service;

import ru.borsch.quizserver.model.Question;

import java.util.Set;

public interface QuestionService {
    Question findById(Long id);

    Set<Question> findBySubjectId(Long subjectId);

}
