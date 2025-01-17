package pl.edu.pjatk.MPR_spring.Service;



import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.edu.pjatk.MPR_spring.exception.CapybaraAlreadyExist;
import pl.edu.pjatk.MPR_spring.exception.CapybaraNotFoundException;
import pl.edu.pjatk.MPR_spring.exception.NoChangesDetectedException;
import pl.edu.pjatk.MPR_spring.model.Capybara;
import pl.edu.pjatk.MPR_spring.repository.CapybaraRepository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

// используется для обозначения класса как сервиса — компонента, который реализует бизнес-логику приложения
@Service
public class CapybaraService {
    private StringUtilsService stringUtilsService;

    private CapybaraRepository repository;

    //CapybaraService
    public CapybaraService(CapybaraRepository repository, StringUtilsService stringUtilsService) {
        this.repository = repository;
        this.stringUtilsService = stringUtilsService;
        this.repository.save(new Capybara("JAkub", "brown"));
        this.repository.save(new Capybara("MAks", "green"));
        this.repository.save(new Capybara("andrey", "black"));
    }

    //getCapybaraByName
    public List<Capybara> getCapybaraByName(String name) {
        Iterable<Capybara> capybaraByNAme = repository.findAll();
        capybaraByNAme.forEach(capybara -> stringUtilsService.goToLowerCaseExceptFirstLetter(String.valueOf(capybara)));
        return repository.findByName(name);
    }

    //getCapybaraByColor
    public List<Capybara> getCapybaraByColor(String color) {
        Iterable<Capybara> capybaraByColor = repository.findAll();
        capybaraByColor.forEach(capybara -> stringUtilsService.goToLowerCaseExceptFirstLetter(String.valueOf(capybara)));
        return repository.findByColor(color);
    }

    //getCapybaraById
    public Optional<Capybara> getCapybaraById(Long id) {
        return repository.findById(id);
    }



    //getCapybaraList
    public Iterable<Capybara> getCapybaraList() {
        Iterable<Capybara> capybaraIterable = repository.findAll();
        capybaraIterable.forEach(capybara -> stringUtilsService.UpperCase(String.valueOf(capybara)));
        return repository.findAll();
    }


    //getCapybara
    public Capybara getCapybara(Long id) {
        Optional<Capybara> capybara = this.repository.findById(id);
        if (capybara.isEmpty()) {
            throw new CapybaraNotFoundException();
        }
        return capybara.get();
    }


    //delete
    public boolean delete(Long id) {
        if (!repository.existsById(id)) {
            throw new CapybaraNotFoundException();
        }
        repository.deleteById(id);
        return true;
    }



    public void add(Capybara capybara) {
        if (repository.existsByNameAndColor(capybara.getName(), capybara.getColor())) {
            System.out.println("Capybara with name " + capybara.getName() + " and color " + capybara.getColor() + " already exists");
            throw new CapybaraAlreadyExist();
        }
        capybara.setName(capybara.getName());
        repository.save(capybara);
    }





    //update
    public Capybara update(Long id, Capybara capybara) {
        // Ищем капибару по id
        Capybara existingCapybara = repository.findById(id)
                .orElseThrow(() -> new CapybaraNotFoundException("Capybara not found with id: " + id));

        // Проверяем, были ли изменения (по имени и цвету)
        if (existingCapybara.getName().equals(capybara.getName()) && existingCapybara.getColor().equals(capybara.getColor())) {
            // Если нет изменений, выбрасываем исключение
            throw new CapybaraAlreadyExist("No changes detected. Capybara already exists with the same name and color.");
        }

        // Обновляем капибару
        existingCapybara.setName(capybara.getName());
        existingCapybara.setColor(capybara.getColor());

        return repository.save(existingCapybara);  // Сохраняем обновленную капибару
    }

    

    //pdf
    public ResponseEntity<byte[]> getPdfCapybara(Long id) {
        Optional<Capybara> optionalCapybara = repository.findById(id);

        if (!optionalCapybara.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // HTTP 404
        }

        Capybara capybara = optionalCapybara.get();

        // Przygotowanie strumienia do generowania PDF w pamięci
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            contentStream.beginText();
            contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);
            contentStream.newLineAtOffset(100, 750);

            // Dodajemy dane Capybara do PDF
            contentStream.showText("Informacje o Capybarze:");
            contentStream.newLineAtOffset(0, -20); // Przesuwamy kursor w dół
            contentStream.showText("ID: " + capybara.getId());
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Imie: " + capybara.getName());
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Kolor: " + capybara.getColor());

            contentStream.endText();
            contentStream.close();

            // Zapisujemy PDF do strumienia
            document.save(baos);
            document.close();

            // Tworzymy nagłówki HTTP do wyświetlenia pliku PDF
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=capybara_info.pdf");
            headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf");

            // Zwracamy PDF w odpowiedzi HTTP (zawartość w pamięci)
            return new ResponseEntity<>(baos.toByteArray(), headers, HttpStatus.OK);

        } catch (IOException e) {
            // Błąd przy generowaniu lub zapisie PDF
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // HTTP 500
        }
    }


}

