package dsd.sockets.trabalho.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "people", schema = "dsd")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class People {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @NotNull
    protected String cpf;

    @NotNull
    protected String name;

    @NotNull
    protected String address;

    public People(String cpf, String name, String address) {
        this.cpf = cpf;
        this.name = name;
        this.address = address;
    }
}
