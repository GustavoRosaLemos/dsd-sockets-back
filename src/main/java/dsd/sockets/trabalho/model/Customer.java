package dsd.sockets.trabalho.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer", schema = "dsd")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends People {
    private Integer score;

    private Double bonus;

    @ManyToMany(mappedBy = "customers", fetch = FetchType.EAGER)
    private List<Order> orders = new ArrayList<>();

    public Customer(@NotNull String cpf, @NotNull String name, @NotNull String address, Integer score, Double bonus) {
        super(cpf, name, address);
        this.score = score;
        this.bonus = bonus;
    }
}
