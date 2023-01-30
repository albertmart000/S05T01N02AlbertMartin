package cat.itacademy.barcelonactiva.S05T01N02AlbertMartin.S05T01N02AlbertMartin.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Transient;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlowerDTO {

    private Long flowerId;
    private String name;
    private String country;
    private String type;

    List<String> EUCountriesList = Arrays.asList("Austria", "Belgium", "Croatia", "Cyprus", "Czechia",
            "Denmark", "Estonia", "Finland", "France", "Germany", "Greece", "Hungary",
            "Ireland", "Italy", "Latvia", "Lithuania", "Luxembourg", "Malta", "Netherlands",
            "Poland", "Portugal", "Romania", "Slovakia", "Slovenia", "Spain", "Sweden");

    public String getType() {
        String type;
        if (EUCountriesList.contains(country)) {
            type = "EU";
        } else {
            type = "Not EU";
        }
        return type;
    }

    @JsonIgnore
    public List<String> getEUCountriesList() {
        return EUCountriesList;
    }
}
