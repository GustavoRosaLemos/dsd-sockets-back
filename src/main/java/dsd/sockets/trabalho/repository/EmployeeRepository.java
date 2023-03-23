package dsd.sockets.trabalho.repository;

import dsd.sockets.trabalho.model.Employee;
import dsd.sockets.trabalho.model.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM dsd.people WHERE cpf = :cpf")
    Optional<Employee> findByCpf(@Param("cpf") String cpf);

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM dsd.people WHERE cpf = :cpf")
    void deleteByCpf(@Param("cpf") String cpf);
}
