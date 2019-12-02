package ru.borsch.quizserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.borsch.quizserver.model.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

}
