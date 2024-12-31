package pl.edu.pjatk.MPR_spring.service;


import org.springframework.beans.factory.annotation.Value;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import pl.edu.pjatk.MPR_spring.exception.CapybaraAlreadyExist;
import pl.edu.pjatk.MPR_spring.model.Capybara;


import java.util.List;

@Service
public class CapybaraClientService {

    @Value("${uriBase}")
    private String uriBase;
    private final WebClient.Builder webClientBuilder;
    private final WebClient webClient;


    public CapybaraClientService(WebClient.Builder webClientBuilder1, WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder1;
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
    }

    public List<Capybara> getCapybarasFromApi() {
        ResponseEntity<List<Capybara>> response = webClient.get()
                .uri("/client/capybara/all")
                .retrieve()
                .toEntity(new ParameterizedTypeReference<List<Capybara>>() {
                })
                .block();
        return response.getBody();
    }


    public void deleteById(final Long id) {
        webClient.delete()
                .uri("/client/capybara/{id}", id)
                .retrieve()
                .toBodilessEntity()
                .subscribe(response -> {
                    if (response.getStatusCode().is2xxSuccessful()) {
                        System.out.println("Capybara deleted successfully");
                    }
                });
    }

    public void addCapybara(Capybara capybara) {
        if (capybara.getName() == null || capybara.getColor() == null) {
            throw new IllegalArgumentException("Capybara name and color must not be null");
        }
        try {
            webClientBuilder.baseUrl("http://localhost:8080")
                    .build()
                    .post()
                    .uri("/client/capybara")
                    .bodyValue(capybara)
                    .retrieve()
                    .toBodilessEntity()
                    .block();
        } catch (Exception e) {
            System.err.println("Error adding capybara: " + e.getMessage());
            e.printStackTrace(); // Для дополнительной информации
        }
    }

    public Capybara getById(Long id) {
        return webClient.get()
                .uri("/client/getById/{id}", id)
                .retrieve()
                .bodyToMono(Capybara.class)
                .block();
    }

    public void updateCapybara(Long id, Capybara capybara) {
        try {
            webClient.put()
                    .uri("/client/capybara/{id}", id)
                    .bodyValue(capybara)
                    .retrieve()
                    .toBodilessEntity()
                    .block();  // Ожидаем завершения операции

        } catch (WebClientResponseException e) {
            if (e.getStatusCode().value() == 400) {  // Если ошибка 400 (BAD_REQUEST)
                System.err.println("Capybara already exists with the same name and color: " + e.getResponseBodyAsString());
                throw new CapybaraAlreadyExist();
            } else {
                System.err.println("Error occurred while updating capybara: " + e.getMessage());
                throw new RuntimeException("Server error: " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            throw new RuntimeException("Unexpected error: " + e.getMessage());
        }
    }
}
