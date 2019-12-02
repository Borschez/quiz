package ru.borsch.quizserver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.borsch.quizserver.model.Subject;
import ru.borsch.quizserver.repositories.SubjectRepository;
import ru.borsch.quizserver.service.SubjectService;

@Service("subjectService")
@Transactional
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public Subject findById(Long id) {
        return subjectRepository.findById(id).orElse(null);
    }
}
