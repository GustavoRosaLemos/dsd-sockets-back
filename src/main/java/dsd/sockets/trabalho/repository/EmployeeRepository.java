package dsd.sockets.trabalho.repository;

import dsd.sockets.trabalho.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
