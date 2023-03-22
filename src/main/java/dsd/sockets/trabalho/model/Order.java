package dsd.sockets.trabalho.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order", schema = "dsd")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String code;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "VARCHAR(255)")
    private OrderStatus status;

    @NotNull
    private Double price;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", columnDefinition = "VARCHAR(255)")
    private PaymentMethods paymentMethod;

    public Order(String code, OrderStatus status, Double price, PaymentMethods paymentMethod) {
        this.code = code;
        this.status = status;
        this.price = price;
        this.paymentMethod = paymentMethod;
    }

    @NotNull
    @OneToMany(mappedBy = "id")
    private List<Customer> customers = new ArrayList<>();

    @OneToMany(mappedBy = "id")
    private List<Employee> employees = new ArrayList<>();
}
