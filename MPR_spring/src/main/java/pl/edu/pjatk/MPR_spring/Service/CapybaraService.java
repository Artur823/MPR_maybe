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
import pl.edu.pjatk.MPR_spring.exception.CapybaraAlredyExist;
import pl.edu.pjatk.MPR_spring.exception.CapybaraNotFoundException;
import pl.edu.pjatk.MPR_spring.model.Capybara;
import pl.edu.pjatk.MPR_spring.repository.CapybaraRepository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CapybaraService {
    private List<Capybara> capybaraList = new ArrayList<>();
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
    public void delete(Integer id) {
        if (!repository.existsById(id.longValue())) {
            throw new CapybaraNotFoundException();
        }
        this.repository.deleteById(id);
    }


    //add
    public void add(Capybara capybara) {
        if (repository.existsById(capybara.getId())) {
            throw new CapybaraAlredyExist();
        }
        stringUtilsService.UpperCase(String.valueOf(capybara));
        this.repository.save(capybara);
    }


    //update
    public void update(String name, String color, Capybara newCapybara) {
        List<Capybara> capybaras = repository.findByName(name);
        if (capybaras.isEmpty()) {
            throw new CapybaraNotFoundException();
        } else if (capybaras.getFirst().getColor().equals(color)) {
            throw new CapybaraAlredyExist();
        }
        capybaras.stream().filter
                (c -> c.getColor().equals(color)).forEach(existing -> {
            existing.setName(newCapybara.getName());
            existing.setColor(newCapybara.getColor());
            repository.save(existing);
        });
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

