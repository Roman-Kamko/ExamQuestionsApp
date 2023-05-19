package edu.skypro.coursework.examquestionsapp.repositories;

import edu.skypro.coursework.examquestionsapp.model.Question;

import java.util.Collection;

public interface QuestionRepository {
    Question add(String question, String answer);

    Question remove(Question question);

    Collection<Question> getAll();
}
