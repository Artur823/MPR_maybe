package pl.edu.pjatk.MPR_spring.service;


import org.springframework.beans.factory.annotation.Value;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;
import pl.edu.pjatk.MPR_spring.model.Capybara;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class CapybaraClientService {

    @Value("${uriBase}")
    private String uriBase;
    private final WebClient.Builder webClientBuilder;
    private final WebClient webClient;

    private final RestClient restClient = RestClient.create();

    public CapybaraClientService(WebClient.Builder webClientBuilder1, WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder1;
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
    }

    public List<Capybara> getCapybarasFromApi() {
        ResponseEntity<List<Capybara>> response = webClient.get()
                .uri("/client/capybara/all")
                .retrieve()
                .toEntity(new ParameterizedTypeReference<List<Capybara>>() {})
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







//    public List<Capybara> getByNameClient(final String name) {
//        return restClient.get()
//                .uri(uriBase + "/client/getByName/" + name)
//                .retrieve()
//                .body(new ParameterizedTypeReference<>() {});
//    }
//
//
//    public Capybara getById(Long id){
//        return restClient.get()
//                .uri(uriBase + "/client/getById/" + id)
//                .retrieve()
//                .body(new ParameterizedTypeReference<>() {});
//    }
//
//    public ResponseEntity<Void> updateCapybara(Capybara capybara){
//        return restClient.post()
//                .uri(uriBase + "/client/updateCapybara" + capybara.getId())
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(capybara)
//                .retrieve()
//                .toBodilessEntity();
//    }
//

}
