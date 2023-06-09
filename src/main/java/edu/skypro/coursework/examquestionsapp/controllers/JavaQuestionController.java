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
@RequestMapping("/java")
public class JavaQuestionController {
    private final QuestionService service;

    public JavaQuestionController(@Qualifier("javaQuestionService")
                                  QuestionService service) {
        this.service = service;
    }

    @GetMapping
    public Collection<Question> getAllJavaQuestions() {
        return service.getAll();
    }

    @GetMapping("/add")
    public Question addJavaQuestion(@RequestParam String question,
                                    @RequestParam String answer) {
        return service.add(question, answer);
    }

    @GetMapping("/remove")
    public Question removeJavaQuestion(@RequestParam String question,
                                       @RequestParam String answer) {
        return service.remove(new Question(question, answer));
    }
}
