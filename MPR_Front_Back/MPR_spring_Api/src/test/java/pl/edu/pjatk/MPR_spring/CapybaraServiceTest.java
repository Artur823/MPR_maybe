package pl.edu.pjatk.MPR_spring;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import pl.edu.pjatk.MPR_spring.Service.CapybaraService;
import pl.edu.pjatk.MPR_spring.exception.CapybaraNotFoundException;
import pl.edu.pjatk.MPR_spring.model.Capybara;
import pl.edu.pjatk.MPR_spring.repository.CapybaraRepository;
import pl.edu.pjatk.MPR_spring.Service.StringUtilsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CapybaraServiceTest {
    @InjectMocks
    private CapybaraService capybaraService;
    @Mock
    private StringUtilsService stringUtilsService;
    @Mock
    private CapybaraRepository repository;




    //czy dziala mock
    @Test
    public void testStringUtilsServiceMock() {
        when(stringUtilsService.UpperCase("test")).thenReturn("TEST");

        String result = stringUtilsService.UpperCase("test");
        assertEquals("TEST", result);

        verify(stringUtilsService).UpperCase("test");
    }


    @Test
    public void getCapybaraByNameTest() {
        List<Capybara> capybaras = new ArrayList<>();

        // Добавляем только одну капибару
        capybaras.add(new Capybara("CapybaraTest", "brown"));

        // Проверьте размер списка
        assertNotNull(capybaras);
        assertEquals(1, capybaras.size());
    }


    @Test
    @MockitoSettings(strictness = Strictness.LENIENT)
    public void getCapybaraNotFoundTest() {
        // Мокируем метод findById репозитория, чтобы он не возвращал капибару (пустой Optional)
        when(repository.findById(1L)).thenReturn(Optional.empty());

        // Ожидаем, что будет выброшено исключение CapybaraNotFoundException
        assertThrows(CapybaraNotFoundException.class, () -> {
            capybaraService.getCapybara(1L);
        });
    }

    @Test
    public void getCapybaraByIdFoundTest() {
        // Создаем мок капибары
        Capybara capybaraMock = new Capybara("TestName", "brown");

        // Мокируем метод findById репозитория, чтобы он возвращал капибару по ID
        when(repository.findById(1L)).thenReturn(Optional.of(capybaraMock));

        // Вызываем метод getCapybaraById
        Optional<Capybara> capybara = capybaraService.getCapybaraById(1L);


        // Проверяем, что капибара найдена и не пустая
        assertTrue(capybara.isPresent(), "Capybara should be present");

        // Проверяем, что капибара совпадает с ожидаемым значением
        assertEquals("TestName", capybara.get().getName());
        assertEquals("brown", capybara.get().getColor());
    }



    @Test
    public void getCapybaraByIdNotFoundTest() {
        // Мокируем метод findById репозитория, чтобы он возвращал пустой Optional (капибара не найдена)
        when(repository.findById(1L)).thenReturn(Optional.empty());

        // Вызываем метод getCapybaraById
        Optional<Capybara> capybara = capybaraService.getCapybaraById(1L);

        // Проверяем, что капибара не найдена (Optional пустой)
        assertFalse(capybara.isPresent());
    }



    @Test
    public void updateCapybaraWithNoChangesTest() {
        // Создаем старую капибару и новую с такими же данными
        Capybara oldCapybara = new Capybara("SameName", "brown");
        Capybara newCapybara = new Capybara("SameName", "brown");

        // Мокируем метод findById, чтобы он возвращал старую капибару по ID
        when(repository.findById(1L)).thenReturn(Optional.of(oldCapybara));

        // Проверяем, что при отсутствии изменений будет выброшено исключение
        assertThrows(RuntimeException.class, () -> capybaraService.update(1L, newCapybara), "No changes detected.");
    }

    @Test
    public void updateCapybaraNotFoundTest() {
        // Мокируем метод findById, чтобы он не находил капибару (пустой Optional)
        when(repository.findById(1L)).thenReturn(Optional.empty());

        // Проверяем, что при отсутствии капибары с таким ID будет выброшено исключение
        assertThrows(CapybaraNotFoundException.class, () -> capybaraService.update(1L, new Capybara("NewName", "green")));
    }

    @Test
    public void verifyUpdateThrowsExceptionTest() {
        // Создаем старую и новую капибару с одинаковыми значениями
        Capybara oldCapybara = new Capybara("SameName", "brown");
        Capybara newCapybara = new Capybara("SameName", "brown");

        // Мокируем метод findById, чтобы он возвращал старую капибару
        when(repository.findById(1L)).thenReturn(Optional.of(oldCapybara));

        // Проверяем, что метод verifyUpdate выбрасывает исключение
        assertThrows(RuntimeException.class, () -> capybaraService.update(1L, newCapybara), "Capybara name and color are the same");
    }



}
