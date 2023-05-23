package edu.skypro.coursework.examquestionsapp.services.impl;

import edu.skypro.coursework.examquestionsapp.exceptions.QuestionNotFoundException;
import edu.skypro.coursework.examquestionsapp.model.Question;
import edu.skypro.coursework.examquestionsapp.repositories.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MathQuestionServiceTest {
    @Mock
    private QuestionRepository repository;
    @InjectMocks
    private MathQuestionService out;

    @BeforeEach
    void beforeEach() {
        when(repository.getAll()).thenReturn(List.of(
                new Question("q1", "a1"),
                new Question("q2", "a2"),
                new Question("q3", "a3")
        ));
    }

    @Test
    void addTest() {
        when(repository.add("q1", "a1")).thenReturn(new Question("q1", "a1"));

        // проверка, что add() возвращается корректный элемент,
        // то что элементы добавляются мы уже проверили в тесте репозитория
        assertThat(out.add("q1", "a1")).isIn(out.getAll());
    }

    @Test
    void removeTest() {
        Question expected = new Question("q1", "a1");

        when(repository.remove(new Question("q1", "a1"))).thenReturn(expected);

        // проверка, что remove() возвращается корректный элемент,
        // то что элементы удаляются мы уже проверили в тесте репозитория
        assertThat(out.remove(new Question("q1", "a1")))
                .isEqualTo(expected);
    }

    @Test
    void removeTestWhenQuestionNotFound() {
        // проверка, что метод remove() выкинет исключение, если вопрос не найден в списке
        assertThatExceptionOfType(QuestionNotFoundException.class)
                .isThrownBy(() -> out.remove(new Question("q1", "a2")));
    }

    @Test
    void getAllTest() {
        // проверка, что возвращается корректный список вопросов
        assertThat(out.getAll()).containsExactlyInAnyOrder(
                new Question("q1", "a1"),
                new Question("q2", "a2"),
                new Question("q3", "a3")
        );
    }

    @Test
    void getRandomQuestion() {
//        Random не мокается, нашел решение, что можно добавить withSettings().withoutAnnotations()
//        final Random random = mock(Random.class, withSettings().withoutAnnotations());

//        Random замокался, но после конструкции when/then или when/thenReturn возвращает случайное число,
//        а не то что указанно
//        when(random.nextInt(3)).thenReturn(0);
//        Question expected = new Question("q1", "a1");
//        assertThat(out.getRandomQuestion())
//                .isEqualTo(expected);

//        проверка, что возвращенный вопрос находится в списке вопросов
        Question actual = out.getRandomQuestion();

        assertThat(actual)
                .isNotNull()
                .isIn(out.getAll());
        // возможно лучше создать сет и заполнить его с помощью .getRandomQuestion(),
        // пока он не заполнится всеми значениями из актуального сета, а затем сравнить
        // с ожидаемым.

        Set<Question> expected = new HashSet<>();

        while (expected.size() < out.getAll().size()) {
            expected.add(out.getRandomQuestion());
        }

        assertThat(out.getAll()).containsExactlyInAnyOrderElementsOf(expected);
    }
}