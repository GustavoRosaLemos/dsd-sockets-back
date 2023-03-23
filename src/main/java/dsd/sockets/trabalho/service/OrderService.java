package dsd.sockets.trabalho.service;

import dsd.sockets.trabalho.model.Order;
import dsd.sockets.trabalho.model.OrderStatus;
import dsd.sockets.trabalho.model.PaymentMethods;
import dsd.sockets.trabalho.model.SocketResponse;
import dsd.sockets.trabalho.model.dto.OrderDTO;
import dsd.sockets.trabalho.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Transactional
    public SocketResponse saveOrder(Order order) {
        try {
            Order result = orderRepository.save(order);
            return new SocketResponse(false, "Pedido criado com sucesso!", result);
        } catch (Exception e) {
            return new SocketResponse(true, "Falha ao criar pedido.", null);
        }
    }

    @Transactional
    public SocketResponse findAllOrders() {
        try {
            List<Order> orders = orderRepository.findAll();
            List<OrderDTO> orderDTOS = orders.stream().map(order -> new OrderDTO(order.getCode(),
                    order.getStatus(),
                    order.getPrice())).toList();
            return new SocketResponse(false, "Busca realizada com sucesso!", orderDTOS);
        } catch (Exception e) {
            return new SocketResponse(true, "Falha ao buscar por pedidos!", null);
        }
    }

    public SocketResponse findOrder(String code) {
        try {
            Optional<Order> order = orderRepository.findByCode(code);
            if (!hasOrders()) {
                return new SocketResponse(true, "Sem pedidos cadastrados.", null);
            }
            if (order.isPresent()) {
                Order orderResult = order.get();
                return new SocketResponse(false, "Busca realizada com sucesso!",
                        new OrderDTO(orderResult.getCode(),
                                orderResult.getStatus(),
                                orderResult.getPrice()));
            } else {
                return new SocketResponse(true, "Pedido não encontrado!", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new SocketResponse(true, "Falha ao buscar por pedido.", null);
        }
    }

    @Transactional
    public SocketResponse deleteOrder(String code) {
        if (!hasOrders()) {
            return new SocketResponse(true, "Sem pedidos cadastrados.", null);
        }
        try {
            orderRepository.deleteByCode(code);
            return new SocketResponse(false, "Pedido excluido com sucesso!", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new SocketResponse(true, "Falha ao excluir o pedido especificado.", null);
        }
    }

    @Transactional
    public SocketResponse confirmOrder(String code, PaymentMethods paymentMethod) {
        try {
            Optional<Order> result = orderRepository.findByCode(code);

            if(result.isEmpty()) {
                return new SocketResponse(true, "Pedido não encontrado.", null);
            }

            Order resultOrder = result.get();
            resultOrder.setPaymentMethod(paymentMethod);
            resultOrder.setStatus(OrderStatus.COMPLETED);
            Order savedOrder = orderRepository.save(resultOrder);
            return new SocketResponse(false, "Pedido confirmado com sucesso!", new OrderDTO(
                    savedOrder.getCode(),
                    savedOrder.getStatus(),
                    savedOrder.getPrice()
            ));
        } catch (Exception e) {
            return new SocketResponse(true, "Falha ao confirmar pedido.", null);
        }
    }

    @Transactional
    public SocketResponse cancelOrder(String code) {
        try {
            Optional<Order> result = orderRepository.findByCode(code);

            if(result.isEmpty()) {
                return new SocketResponse(true, "Pedido não encontrado.", null);
            }

            Order resultOrder = result.get();
            resultOrder.setStatus(OrderStatus.CANCELED);
            Order savedOrder = orderRepository.save(resultOrder);
            return new SocketResponse(false, "Pedido cancelado com sucesso!", new OrderDTO(
                    savedOrder.getCode(),
                    savedOrder.getStatus(),
                    savedOrder.getPrice()
            ));
        } catch (Exception e) {
            return new SocketResponse(true, "Falha ao cancelar pedido.", null);
        }
    }

    public boolean hasOrders() {
        return !(orderRepository.findAll().size() == 0);
    }
}
