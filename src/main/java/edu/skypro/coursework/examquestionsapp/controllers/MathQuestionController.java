package edu.skypro.coursework.examquestionsapp.controllers;

import edu.skypro.coursework.examquestionsapp.model.Question;
import edu.skypro.coursework.examquestionsapp.services.QuestionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/math")
public class MathQuestionController {
    private QuestionService service;

    public MathQuestionController(@Qualifier("mathQuestionService")
                                  QuestionService service) {
        this.service = service;
    }

    @GetMapping
    public Collection<Question> getAllMathQuestions() {
        return service.getAll();
    }

    @GetMapping("/add")
    public Question addMathQuestion(@RequestParam String question,
                                    @RequestParam String answer) {
        return service.add(question, answer);
    }

    @GetMapping("/remove")
    public Question removeMathQuestion(@RequestParam String question,
                                       @RequestParam String answer) {
        return service.remove(new Question(question, answer));
    }
}
