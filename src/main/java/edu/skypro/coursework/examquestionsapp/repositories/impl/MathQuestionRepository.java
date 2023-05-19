package edu.skypro.coursework.examquestionsapp.repositories.impl;

import edu.skypro.coursework.examquestionsapp.model.Question;
import edu.skypro.coursework.examquestionsapp.repositories.QuestionRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Repository
public class MathQuestionRepository implements QuestionRepository {
    private final Set<Question> mathQuestion = new HashSet<>();

    @PostConstruct
    public void addSomeQuestion() {
        mathQuestion.add(new Question("Math question 1", "Math answer 1"));
        mathQuestion.add(new Question("Math question 2", "Math answer 2"));
        mathQuestion.add(new Question("Math question 3", "Math answer 3"));
    }

    @Override
    public Question add(String question, String answer) {
        Question result = new Question(question, answer);
        mathQuestion.add(result);
        return result;
    }

    @Override
    public Question remove(Question question) {
        mathQuestion.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return mathQuestion;
    }
}
