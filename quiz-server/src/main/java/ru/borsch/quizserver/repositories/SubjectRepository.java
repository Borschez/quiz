package ru.borsch.quizserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.borsch.quizserver.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

}
