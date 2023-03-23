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
public class EmployeeDTO extends PeopleDTO {
    @JsonProperty("salary")
    private Double salary;
    @JsonProperty("role")
    private String role;

    public EmployeeDTO(String cpf, String name, String address, Double salary, String role) {
        super(cpf, name, address);
        this.salary = salary;
        this.role = role;
    }
}
