package edu.skypro.coursework.examquestionsapp.repositories.impl;

import edu.skypro.coursework.examquestionsapp.model.Question;
import edu.skypro.coursework.examquestionsapp.repositories.QuestionRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Repository
public class JavaQuestionRepository implements QuestionRepository {
    private final Set<Question> javaQuestion = new HashSet<>();

    @PostConstruct
    public void addSomeQuestion() {
        javaQuestion.add(new Question("Java question 1", "Java answer 1"));
        javaQuestion.add(new Question("Java question 2", "Java answer 2"));
        javaQuestion.add(new Question("Java question 3", "Java answer 3"));
    }

    @Override
    public Question add(String question, String answer) {
        Question result = new Question(question, answer);
        javaQuestion.add(result);
        return result;
    }

    @Override
    public Question remove(Question question) {
        javaQuestion.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return javaQuestion;
    }
}
