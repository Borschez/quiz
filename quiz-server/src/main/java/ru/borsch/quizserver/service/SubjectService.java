package ru.borsch.quizserver.service;

import ru.borsch.quizserver.model.Answer;
import ru.borsch.quizserver.model.Subject;

public interface SubjectService {
    Subject findById(Long id);
}
