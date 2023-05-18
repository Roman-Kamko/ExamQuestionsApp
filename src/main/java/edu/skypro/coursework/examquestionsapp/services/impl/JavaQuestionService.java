package edu.skypro.coursework.examquestionsapp.services.impl;

import edu.skypro.coursework.examquestionsapp.model.Question;
import edu.skypro.coursework.examquestionsapp.services.QuestionService;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class JavaQuestionService implements QuestionService {
    private static Set<Question> questionSet = new HashSet<>();
    @Override
    public Question add(String question, String answer) {
        Question result = new Question(question, answer);
        questionSet.add(result);
        return result;
    }

    @Override
    public Question add(Question question) {
        questionSet.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        questionSet.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return questionSet;
    }

    @Override
    public Question getRandomQuestion() {
        Random random = new Random();
        List<Question> questionList = questionSet.stream().toList();
        return questionList.get(random.nextInt(questionList.size()));
    }
}
