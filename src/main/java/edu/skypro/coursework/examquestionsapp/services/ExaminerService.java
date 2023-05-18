package edu.skypro.coursework.examquestionsapp.services;

import edu.skypro.coursework.examquestionsapp.model.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}
