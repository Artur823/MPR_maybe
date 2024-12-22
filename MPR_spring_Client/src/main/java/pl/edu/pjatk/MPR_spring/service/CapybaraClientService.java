package pl.edu.pjatk.MPR_spring.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;
import pl.edu.pjatk.MPR_spring.model.Capybara;

import java.util.List;

@Service
public class CapybaraClientService {

    @Value("${uriBase}")
    private String uriBase;
    private final WebClient webClient;

    private final RestClient restClient = RestClient.create();

    public CapybaraClientService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
    }

    public List<Capybara> getAll(){
        return restClient.get()
                .uri(uriBase + "/allCapybara")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }
    public List<Capybara> getByName(final String name) {
        return restClient.get()
                .uri(uriBase + "/getByName/" + name)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    public ResponseEntity<Void> addCapybara(Capybara capybara) {
        capybara.generateHashCode();
        return restClient.post()
                .uri(uriBase + "/addForm")
                .contentType(MediaType.APPLICATION_JSON)
                .body(capybara)
                .retrieve()
                .toBodilessEntity();
    }

    public ResponseEntity<Void> deleteByName(final String name) {
        return restClient.delete()
                .uri(uriBase + "/deleteCapybara/" + name)
                .retrieve()
                .toBodilessEntity();
    }

    public Capybara getById(Long id){
        return restClient.get()
                .uri(uriBase + "/getById/" + id)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    public ResponseEntity<Void> updateCapybara(Capybara capybara){
        return restClient.post()
                .uri(uriBase + "/updateCapybara/" + capybara.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .body(capybara)
                .retrieve()
                .toBodilessEntity();
    }
    public List<Object> getCapybarasFromApi() {
        ResponseEntity<List> response = webClient.get()
                .uri("/api/capybara/all")
                .retrieve()
                .toEntity(List.class)
                .block();

        return response.getBody();
    }
}
