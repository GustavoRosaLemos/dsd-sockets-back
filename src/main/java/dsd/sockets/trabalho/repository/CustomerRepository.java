package dsd.sockets.trabalho.repository;

import dsd.sockets.trabalho.model.Customer;
import dsd.sockets.trabalho.model.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM dsd.people WHERE cpf = :cpf")
    Optional<Customer> findByCpf(@Param("cpf") String cpf);

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM dsd.people WHERE cpf = :cpf")
    void deleteByCpf(@Param("cpf") String cpf);
}
