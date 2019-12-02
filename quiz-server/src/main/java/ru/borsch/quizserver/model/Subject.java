package ru.borsch.quizserver.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Set;


@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1024)
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Subject_Question",
            joinColumns = @JoinColumn(name = "subjectId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "questionId", referencedColumnName = "id"))
    private Set<Question> questions;
}
