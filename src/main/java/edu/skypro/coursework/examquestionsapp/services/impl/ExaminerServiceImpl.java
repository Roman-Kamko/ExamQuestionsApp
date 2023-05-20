package edu.skypro.coursework.examquestionsapp.services.impl;

import edu.skypro.coursework.examquestionsapp.exceptions.InvalidInputException;
import edu.skypro.coursework.examquestionsapp.exceptions.QuestionNotFoundException;
import edu.skypro.coursework.examquestionsapp.model.Question;
import edu.skypro.coursework.examquestionsapp.services.ExaminerService;
import edu.skypro.coursework.examquestionsapp.services.QuestionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

@Service
public class ExaminerServiceImpl implements ExaminerService {
//    private final QuestionService javaQuestionService;
//    private final QuestionService mathQuestionService;

    private final List<QuestionService> services;

    public ExaminerServiceImpl(List<QuestionService> services) {
        this.services = services;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {

        Collection<Question> examQuestionSet = new HashSet<>();

        int totalCapacity = services.stream()
                .mapToInt(element -> element.getAll().size())
                .sum();

        if (amount > totalCapacity) {
            throw new InvalidInputException();
        }

        while (examQuestionSet.size() < amount) {
            examQuestionSet.add(randomQuestion());
        }
        return examQuestionSet;
    }

    private Question randomQuestion() {
        Random random = new Random();
        if (random.nextInt() > 0.5) {
            return javaQuestion();
        } else {
            return mathQuestion();
        }
    }

    private Question javaQuestion() {
        return services.stream()
                .map(QuestionService::getRandomQuestion)
                .findFirst()
                .orElseThrow(QuestionNotFoundException::new);
    }

    private Question mathQuestion() {
        return services.stream()
                .map(QuestionService::getRandomQuestion)
                .skip(1)
                .findFirst()
                .orElseThrow(QuestionNotFoundException::new);
    }
}
