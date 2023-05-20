package edu.skypro.coursework.examquestionsapp.services.impl;

import edu.skypro.coursework.examquestionsapp.exceptions.InvalidInputException;
import edu.skypro.coursework.examquestionsapp.exceptions.QuestionNotFoundException;
import edu.skypro.coursework.examquestionsapp.model.Question;
import edu.skypro.coursework.examquestionsapp.repositories.QuestionRepository;
import edu.skypro.coursework.examquestionsapp.services.QuestionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
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
//        return generateQuestion();
        Random random = new Random();
        return repository.getAll().stream()
                .skip(random.nextInt(repository.getAll().size()))
                .findFirst()
                .orElseThrow(QuestionNotFoundException::new);

    }

//    private Question generateQuestion() {
//        Random random = new Random();
//        List<Integer> integers = List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
//        List<Character> operations = List.of('+', '-', '*', '/');
//
//        int num1 = integers.get(random.nextInt(integers.size()));
//        int num2 = integers.get(random.nextInt(integers.size()));
//        char operation = operations.get(random.nextInt(operations.size()));
//
//        String answer = null;
//        String question = num1 + " " + operation + " " + num2;
//
//        if (divisionByZero(operation, num2)) {
//            answer = "Делить на ноль нельзя";
//        } else {
//            switch (operation) {
//                case '+' -> answer = String.valueOf(num1 + num2);
//                case '-' -> answer = String.valueOf(num1 - num2);
//                case '*' -> answer = String.valueOf(num1 * num2);
//                case '/' -> answer = String.valueOf(num1 / num2);
//            }
//        }
//
//        return new Question(question, answer);
//    }
//
//    private Boolean divisionByZero(char operation, int num2) {
//        return operation == '/' && num2 == 0;
//    }
}
