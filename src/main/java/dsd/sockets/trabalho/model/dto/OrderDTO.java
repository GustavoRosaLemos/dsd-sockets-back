package dsd.sockets.trabalho.model.dto;

import dsd.sockets.trabalho.model.Customer;
import dsd.sockets.trabalho.model.Employee;
import dsd.sockets.trabalho.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private String code;
    private OrderStatus status;
    private Double price;
}
