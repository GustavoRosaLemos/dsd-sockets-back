package dsd.sockets.trabalho.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employee", schema = "dsd")
@Getter @Setter
@NoArgsConstructor
public class Employee extends People {
    private Double salary;

    private String role;

    public Employee(String cpf, String name, String address, Double salary, String role) {
        super(cpf, name, address);
        this.salary = salary;
        this.role = role;
    }
}
