package dsd.sockets.trabalho.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "customer", schema = "dsd")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends People {
    private Integer score;

    private Double bonus;

    public Customer(@NotNull String cpf, @NotNull String name, @NotNull String address, Integer score, Double bonus) {
        super(cpf, name, address);
        this.score = score;
        this.bonus = bonus;
    }
}
