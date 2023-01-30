package cat.itacademy.barcelonactiva.S05T01N02AlbertMartin.S05T01N02AlbertMartin.controllers;

import cat.itacademy.barcelonactiva.S05T01N02AlbertMartin.S05T01N02AlbertMartin.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.S05T01N02AlbertMartin.S05T01N02AlbertMartin.model.service.IFlowerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flowers")
@AllArgsConstructor
public class FlowerRestController {

    private final IFlowerService flowerService;

    @PostMapping()
    public ResponseEntity<FlowerDTO> addFlower(@RequestBody FlowerDTO flowerDTO) {
        try {
            FlowerDTO newFlowerDTO = flowerService.addFlower(flowerDTO);
            return new ResponseEntity<>(newFlowerDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{flowerId}")
    public ResponseEntity<FlowerDTO> getFlower(@PathVariable Long flowerId) {
        Optional<FlowerDTO> optionalFlower = Optional.ofNullable(this.flowerService.getFlowerById(flowerId));
        if (optionalFlower.isPresent()) {
            return new ResponseEntity<>(optionalFlower.get(), HttpStatus.OK);
        } else {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", "No s'ha trobat cap flor amb el id: " + flowerId);
            return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{flowerId}")
    public ResponseEntity<FlowerDTO> updateFlower(@PathVariable Long flowerId,
                                                  @RequestBody FlowerDTO flowerDTO) {
        FlowerDTO flower = flowerService.updateFlower(flowerId, flowerDTO);
        if (flower != null) {
            return new ResponseEntity<>(flower, HttpStatus.OK);
        } else {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Error", "No s'ha trobat cap flor amb el id: " + flowerId);
            return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{flowerId}")
    public ResponseEntity<String> deleteFlower(@PathVariable Long flowerId) {
        try {
            flowerService.deleteFlower(flowerId);
            return new ResponseEntity<>("S'ha esborrat la flor amb el id: " + flowerId,
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("No s'ha trobat cap flor amb el id: " + flowerId,
                    HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping()
    public ResponseEntity<List<FlowerDTO>> getAllFlowers() {
        try {
            List<FlowerDTO> flowerDTOList = flowerService.getAllFlowers();
            if (flowerDTOList.isEmpty()) {
                HttpHeaders headers = new HttpHeaders();
                headers.add("Error", "No n'hi ha cap flor a la llista");
                return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(flowerDTOList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}