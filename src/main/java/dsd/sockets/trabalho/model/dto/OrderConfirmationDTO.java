package dsd.sockets.trabalho.model.dto;

import dsd.sockets.trabalho.model.PaymentMethods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderConfirmationDTO {
    private String code;
    private PaymentMethods paymentMethod;
}
