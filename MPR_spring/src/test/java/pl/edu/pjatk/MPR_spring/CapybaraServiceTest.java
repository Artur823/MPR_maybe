package pl.edu.pjatk.MPR_spring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.edu.pjatk.MPR_spring.Service.CapybaraService;
import pl.edu.pjatk.MPR_spring.model.Capybara;
import pl.edu.pjatk.MPR_spring.repository.CapybaraRepository;
import pl.edu.pjatk.MPR_spring.Service.StringUtilsService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class CapybaraServiceTest {
    private CapybaraService capybaraService;
    private StringUtilsService stringUtilsService;

    @BeforeEach
    public void setUp() {
        this.stringUtilsService = Mockito.mock(StringUtilsService.class);
        CapybaraRepository capybaraRepository = Mockito.mock(CapybaraRepository.class);
        this.capybaraService = new CapybaraService(capybaraRepository, stringUtilsService);
    }

    @Test
    public void addCapybaraTest() {
        Capybara capybara = new Capybara("Capibaratestadd", "Green");
        capybaraService.add(capybara);
        verify(stringUtilsService, times(1)).UpperCase(String.valueOf(capybara));
        verify(stringUtilsService, times(0)).goToLowerCaseExceptFirstLetter(String.valueOf(capybara));
    }

    @Test
    public void getCapybarasListTest() {
        List<Capybara> capybara = (List<Capybara>) capybaraService.getCapybaraList();
        verify(stringUtilsService, times(capybara.size())).goToLowerCaseExceptFirstLetter(any());
        verify(stringUtilsService, times(3)).UpperCase(any());
    }

    @Test
    public void getCapybaraByNameTest() {
        List<Capybara> capybara = capybaraService.getCapybaraByName("andrey");
        verify(stringUtilsService, times(capybara.size())).goToLowerCaseExceptFirstLetter(any());
        verify(stringUtilsService, times((3))).UpperCase(any());
    }

    @Test
    public void getCapybarasListByColorTest() {
        List<Capybara> capybara = capybaraService.getCapybaraByColor("green");
        verify(stringUtilsService, times(capybara.size())).goToLowerCaseExceptFirstLetter(any());
        verify(stringUtilsService, times(3)).UpperCase(any());
    }

    @Test
    public void getCapybaraTest() {
        Capybara capybara = capybaraService.getCapybara(1L);
        if(capybara != null){
            verify(stringUtilsService, times(1)).goToLowerCaseExceptFirstLetter(any());
        }
        else{
            verify(stringUtilsService, times(0)).goToLowerCaseExceptFirstLetter(any());
        }
        verify(stringUtilsService, times(3)).UpperCase(any());
    }

}
