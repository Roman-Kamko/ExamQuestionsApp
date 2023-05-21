package edu.skypro.coursework.examquestionsapp.repositories.impl;

import edu.skypro.coursework.examquestionsapp.model.Question;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MathQuestionRepositoryTest {
    private MathQuestionRepository out = new MathQuestionRepository();

    @BeforeEach
    public void beforeEach() {
        out.add("q1", "a1");
        out.add("q2", "a2");
        out.add("q3", "a3");
    }

    @AfterEach
    public void afterEach() {
        out.getAll().clear();
    }

    @Test
    void addSomeQuestionTest() {
        // нужно очистить коллекцию от элементов добавленных методом beforeEach()
        out.getAll().clear();
        out.addSomeQuestion();
        assertThat(out.getAll()).containsExactlyInAnyOrder(
                new Question("Math question 1", "Math answer 1"),
                new Question("Math question 2", "Math answer 2"),
                new Question("Math question 3", "Math answer 3")
        );
    }

    @Test
    void addTest() {
        int beforeCount = out.getAll().size();

        Question expected = new Question("q4", "a4");

        assertThat(out.add("q4", "a4"))
                .isEqualTo(expected)
                .isIn(out.getAll());

        assertThat(out.getAll()).hasSize(beforeCount + 1);
    }

    @Test
    void removeTest() {
        int beforeCount = out.getAll().size();

        Question expected = new Question("q3", "a3");

        assertThat(out.remove(new Question("q3", "a3")))
                .isEqualTo(expected)
                .isNotIn(out.getAll());

        assertThat(out.getAll()).hasSize(beforeCount - 1);
    }

    @Test
    void getAllTest() {
        assertThat(out.getAll())
                .hasSize(3)
                .containsExactlyInAnyOrder(
                        new Question("q1", "a1"),
                        new Question("q2", "a2"),
                        new Question("q3", "a3")
                );
    }
}