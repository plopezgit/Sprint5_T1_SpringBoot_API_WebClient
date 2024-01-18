package cat.itacademy.barcelonactiva.lopez.pedro.s05.t01.n03.model.service;

import cat.itacademy.barcelonactiva.lopez.pedro.s05.t01.n03.model.dto.FlowerDTO;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface FlowerServiceInterface {

    Flux<FlowerDTO> getFlowers();
    Mono<FlowerDTO> getFlower(int id);
    Mono<ClientResponse> createFlower (FlowerDTO flowerDTO);
    Mono<FlowerDTO> updateFlower (int id, FlowerDTO flowerDTO);
    Mono<Void> deleteFlower (int id);
}
