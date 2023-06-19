package edu.skypro.coursework.examquestionsapp.services.impl;

import edu.skypro.coursework.examquestionsapp.exceptions.InvalidInputException;
import edu.skypro.coursework.examquestionsapp.model.Question;
import edu.skypro.coursework.examquestionsapp.services.QuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {
    @Mock()
    private QuestionService service;
    @InjectMocks
    private ExaminerServiceImpl out;

    @BeforeEach
    void beforeEach() {
        when(service.getAll()).thenReturn(List.of(new Question("q1", "a1")));

    }

    @Test
    void getQuestionTest() {
        when(service.getRandomQuestion()).thenReturn(new Question("q1", "a1"));
        assertThat(out.getQuestions(1)).containsExactlyInAnyOrder(new Question("q1", "a1"));
    }

    @Test
    void getQuestionNegativeTest() {
        assertThatExceptionOfType(InvalidInputException.class)
                .isThrownBy(() -> out.getQuestions(3));
    }
}