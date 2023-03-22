package dsd.sockets.trabalho.repository;

import dsd.sockets.trabalho.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
