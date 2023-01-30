package cat.itacademy.barcelonactiva.S05T01N02AlbertMartin.S05T01N02AlbertMartin.model.service;

import cat.itacademy.barcelonactiva.S05T01N02AlbertMartin.S05T01N02AlbertMartin.model.domain.Flower;
import cat.itacademy.barcelonactiva.S05T01N02AlbertMartin.S05T01N02AlbertMartin.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.S05T01N02AlbertMartin.S05T01N02AlbertMartin.model.repository.IFlowerRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class FlowerServiceImpl implements IFlowerService {

    private final IFlowerRepository flowerRepository;
    private final ModelMapper modelMapper;

    @Override
    public FlowerDTO addFlower(FlowerDTO flowerDTO) {
        Flower flower = mapDTOToEntity(flowerDTO);
        Flower newFlower = flowerRepository.save(flower);
        return mapEntityToDTO(newFlower);
    }

    @Override
    public FlowerDTO getFlowerById(Long flowerId) {
        Optional<Flower> optionalFlower = flowerRepository.findById(flowerId);
        if (optionalFlower.isPresent()) {
            Flower flower = optionalFlower.get();
            return mapEntityToDTO(flower);
        }
        return null;
    }

    @Override
    public FlowerDTO updateFlower(Long flowerId, FlowerDTO flowerDTO) {
        Optional<Flower> optionalFlower = flowerRepository.findById(flowerId);
        Flower flowerToUpdate;
        if (optionalFlower.isPresent()) {
            flowerToUpdate = optionalFlower.get();
            flowerToUpdate.setName(flowerDTO.getName());
            flowerToUpdate.setCountry(flowerDTO.getCountry());
            Flower updatedFlower = flowerRepository.save(flowerToUpdate);
            return mapEntityToDTO(updatedFlower);
        }
        return null;
    }

    @Override
    public void deleteFlower(Long flowerId) {
        flowerRepository.deleteById(flowerId);
    }

    @Override
    public List<FlowerDTO> getAllFlowers() {
        List<Flower> flowerList = flowerRepository.findAll();
        return flowerList.stream()
                .map(this::mapEntityToDTO)
                .collect(Collectors.toList());
    }

    private FlowerDTO mapEntityToDTO(Flower flower) {
        return modelMapper.map(flower, FlowerDTO.class);
    }

    private Flower mapDTOToEntity(FlowerDTO flowerDTO) {
        return modelMapper.map(flowerDTO, Flower.class);
    }

}
