package ru.borsch.quizserver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.borsch.quizserver.model.Answer;
import ru.borsch.quizserver.repositories.AnswerRepository;
import ru.borsch.quizserver.service.AnswerService;

import org.springframework.transaction.annotation.Transactional;


import java.util.Set;

@Service("answerService")
@Transactional
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public Answer saveAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    @Override
    public void deleteAnswer(Answer answer) {
        answerRepository.delete(answer);
    }

    @Override
    public Answer findById(Long id) {
        return answerRepository.findById(id).orElse(null);
    }

    @Override
    public Set<Answer> findByQuestionId(Long questionId) {
        return null;
    }
}
