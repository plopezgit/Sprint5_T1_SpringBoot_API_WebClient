package cat.itacademy.barcelonactiva.lopez.pedro.s05.t01.n03.model.service;

import cat.itacademy.barcelonactiva.lopez.pedro.s05.t01.n03.model.dto.FlowerDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Service
public class FlowerService implements FlowerServiceInterface{

    @Value("${spring.external.service.base-url}")
    private String basePath;
    private final WebClient webClient;

    public FlowerService (WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(basePath).build();
    }

    public Flux<FlowerDTO> getFlowers() {
        return this.webClient.get().uri("/getAll")
                .retrieve()
                .bodyToFlux(FlowerDTO.class);
    }

    @Override
    public Mono<FlowerDTO> getFlower(int id) {
        return this.webClient.get().uri("/getOne/{id}", id)
                .retrieve()
                .bodyToMono(FlowerDTO.class);
    }

    public Mono<ClientResponse> createFlower (FlowerDTO flowerDTO) {
        return this.webClient.post().uri("/add")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(flowerDTO), FlowerDTO.class)
                .exchange();
    }

    public Mono<FlowerDTO> updateFlower (int id, FlowerDTO flowerDTO) {
        return this.webClient.post().uri("/update/{id}", id)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(flowerDTO), FlowerDTO.class)
                .retrieve()
                .bodyToMono(FlowerDTO.class);
    }

    public Mono<Void> deleteFlower (int id) {
        return this.webClient.delete().uri("delete/{id}", id)
                .retrieve()
                .bodyToMono(Void.class);
    }
}
