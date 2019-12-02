package ru.borsch.quizserver.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1024)
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Question_CorrectAnswer",
            joinColumns = @JoinColumn(name = "questionId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "answerId", referencedColumnName = "id"))
    private Set<Answer> correctAnswers;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Question_AnswerOptions",
            joinColumns = @JoinColumn(name = "questionId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "answerId", referencedColumnName = "id"))
    private Set<Answer> answerOptions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Answer> getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(Set<Answer> correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public Set<Answer> getAnswerOptions() {
        return answerOptions;
    }

    public void setAnswerOptions(Set<Answer> answerOptions) {
        this.answerOptions = answerOptions;
    }
}
