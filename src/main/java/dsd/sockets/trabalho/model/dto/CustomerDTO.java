package dsd.sockets.trabalho.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO extends PeopleDTO {
    @JsonProperty("score")
    private Integer score;
    @JsonProperty("bonus")
    private Double bonus;

    public CustomerDTO(String cpf, String name, String address, Integer score, Double bonus) {
        super(cpf, name, address);
        this.score = score;
        this.bonus = bonus;
    }
}
