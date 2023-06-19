package edu.skypro.coursework.examquestionsapp.services.impl;

import edu.skypro.coursework.examquestionsapp.exceptions.InvalidInputException;
import edu.skypro.coursework.examquestionsapp.model.Question;
import edu.skypro.coursework.examquestionsapp.services.ExaminerService;
import edu.skypro.coursework.examquestionsapp.services.QuestionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService javaQuestionService;
    private final QuestionService mathQuestionService;

//    private final List<QuestionService> services;

//    public ExaminerServiceImpl(List<QuestionService> services) {
//        this.services = services;
//    }


    public ExaminerServiceImpl(
            @Qualifier("javaQuestionService") QuestionService javaQuestionService,
            @Qualifier("mathQuestionService") QuestionService mathQuestionService) {
        this.javaQuestionService = javaQuestionService;
        this.mathQuestionService = mathQuestionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {

        Collection<Question> examQuestionSet = new HashSet<>();

//        int totalCapacity = services.stream()
//                .mapToInt(element -> element.getAll().size())
//                .sum();

        int totalCapacity = javaQuestionService.getAll().size() +
                mathQuestionService.getAll().size();

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
            return javaQuestionService.getRandomQuestion();
        } else {
            return mathQuestionService.getRandomQuestion();
        }
    }

//    private Question javaQuestion() {
//        return services.stream()
//                .map(QuestionService::getRandomQuestion)
//                .findFirst()
//                .orElseThrow(QuestionNotFoundException::new);
//    }

//    private Question mathQuestion() {
//        return services.stream()
//                .map(QuestionService::getRandomQuestion)
//                .skip(1)
//                .findFirst()
//                .orElseThrow(QuestionNotFoundException::new);
//    }
}
