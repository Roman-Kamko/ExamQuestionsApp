package edu.skypro.coursework.examquestionsapp.services.impl;

import edu.skypro.coursework.examquestionsapp.exceptions.InvalidInputException;
import edu.skypro.coursework.examquestionsapp.model.Question;
import edu.skypro.coursework.examquestionsapp.repositories.QuestionRepository;
import edu.skypro.coursework.examquestionsapp.services.QuestionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Random;

@Service
public class MathQuestionService implements QuestionService {
    private final QuestionRepository repository;

    public MathQuestionService(@Qualifier("mathQuestionRepository")
                               QuestionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Question add(String question, String answer) {
        return add(repository.add(question, answer));
    }

    @Override
    public Question add(Question question) {
        return question;
    }
    // не нужный метод)

    @Override
    public Question remove(Question question) {
        return repository.remove(question);
    }

    @Override
    public Collection<Question> getAll() {
        return repository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        Random random = new Random();
        return repository.getAll().stream()
                .skip(random.nextInt((repository.getAll().size()) - 1))
                .findFirst()
                .orElseThrow(InvalidInputException::new);
    }
}
