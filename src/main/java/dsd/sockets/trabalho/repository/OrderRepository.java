package dsd.sockets.trabalho.repository;

import dsd.sockets.trabalho.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
