package dsd.sockets.trabalho.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employee", schema = "dsd")
@Getter @Setter
@NoArgsConstructor
public class Employee extends People {
    private Double salary;

    private String role;

    @ManyToMany(mappedBy = "employees", fetch = FetchType.EAGER)
    private List<Order> orders = new ArrayList<>();

    public Employee(String cpf, String name, String address, Double salary, String role) {
        super(cpf, name, address);
        this.salary = salary;
        this.role = role;
    }
}
