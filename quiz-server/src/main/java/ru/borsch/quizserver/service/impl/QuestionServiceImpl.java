package ru.borsch.quizserver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.borsch.quizserver.model.Question;
import ru.borsch.quizserver.repositories.QuestionRepository;
import ru.borsch.quizserver.service.QuestionService;

import java.util.Set;

@Service("questionService")
@Transactional
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Question findById(Long id) {
        return questionRepository.findById(id).orElse(null);
    }

    @Override
    public Set<Question> findBySubjectId(Long subjectId) {
        return null;
    }
}
