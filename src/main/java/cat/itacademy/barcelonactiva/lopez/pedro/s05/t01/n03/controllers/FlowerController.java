package cat.itacademy.barcelonactiva.lopez.pedro.s05.t01.n03.controllers;

import cat.itacademy.barcelonactiva.lopez.pedro.s05.t01.n03.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.lopez.pedro.s05.t01.n03.model.service.FlowerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientFlowers")
@RequiredArgsConstructor
public class FlowerController {

    private final FlowerService flowerService;

    @Operation(summary = "It gets all the flowers.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Flower list successfully.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FlowerDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Flower Not found.",
                    content = @Content)
    })
    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllFlowers () {
        return new ResponseEntity<>(flowerService.getFlowers(), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createFlower (@RequestBody FlowerDTO flowerDTO) {
        flowerService.createFlower(flowerDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateFlower (@PathVariable("id") int id, @RequestBody FlowerDTO flowerDTO) {
        flowerService.updateFlower(id, flowerDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteFlower(@PathVariable("id") int id) {
        flowerService.deleteFlower(id);
    }


}
