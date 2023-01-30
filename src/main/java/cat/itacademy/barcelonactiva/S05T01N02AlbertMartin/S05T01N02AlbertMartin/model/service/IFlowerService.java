package cat.itacademy.barcelonactiva.S05T01N02AlbertMartin.S05T01N02AlbertMartin.model.service;

import cat.itacademy.barcelonactiva.S05T01N02AlbertMartin.S05T01N02AlbertMartin.model.dto.FlowerDTO;
import java.util.List;

public interface IFlowerService {

    FlowerDTO addFlower(FlowerDTO flowerDTO);

    FlowerDTO getFlowerById(Long Id);

    FlowerDTO updateFlower(Long Id, FlowerDTO flowerDTO);

    void deleteFlower(Long Id);

    List<FlowerDTO> getAllFlowers();
}
