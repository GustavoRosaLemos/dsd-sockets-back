package dsd.sockets.trabalho.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.*;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", columnDefinition = "VARCHAR(255)")
    private PaymentMethods paymentMethod;

    public Order(String code, OrderStatus status, Double price, Set<Customer> customers, Set<Employee> employees) {
        this.code = code;
        this.status = status;
        this.price = price;
        this.customers = customers;
        this.employees = employees;
    }

    public Order(String code, OrderStatus status, Double price, Set<Customer> customers) {
        this.code = code;
        this.status = status;
        this.price = price;
        this.customers = customers;
    }

    @NotNull
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "customer_order",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id"), schema = "dsd")
    private Set<Customer> customers = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "employee_order",
    joinColumns = @JoinColumn(name = "order_id"),
    inverseJoinColumns = @JoinColumn(name = "employee_id"), schema = "dsd")
    private Set<Employee> employees = new HashSet<>();
}
