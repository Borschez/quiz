package ru.borsch.quizserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.borsch.quizserver.model.Question;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    //@Query("select q from Subject s left outer join s.questions q where d.owner.id= ?1 or d_u.id = ?2")
    //List<Question> findByThemeId(Long themeId);
}
