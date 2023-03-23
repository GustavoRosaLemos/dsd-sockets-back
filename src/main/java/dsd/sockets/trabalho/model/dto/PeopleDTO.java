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
public class PeopleDTO {
    @JsonProperty("cpf")
    protected String cpf;
    @JsonProperty("name")
    protected String name;
    @JsonProperty("address")
    protected String address;
}
