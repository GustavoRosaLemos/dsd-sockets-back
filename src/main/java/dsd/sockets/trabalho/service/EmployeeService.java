package dsd.sockets.trabalho.service;

import dsd.sockets.trabalho.model.Employee;
import dsd.sockets.trabalho.model.People;
import dsd.sockets.trabalho.model.SocketResponse;
import dsd.sockets.trabalho.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Transactional
    public SocketResponse saveEmployee(Employee employee) {
        try {
            Employee result = employeeRepository.save(employee);
            return new SocketResponse(false, "Funcionario adicionado com sucesso!", result);
        } catch (Exception e) {
            return new SocketResponse(true, "Falha ao adicionar funcionario.", null);
        }
    }

    public SocketResponse findAllEmployee() {
        try {
            List<Employee> employees = employeeRepository.findAll();
            return new SocketResponse(false, "Busca realizada com sucesso!", employees);
        } catch (Exception e) {
            return new SocketResponse(true, "Falha ao buscar por funcinarios!", null);
        }
    }

    public SocketResponse findEmployee(String cpf) {
        try {
            Optional<Employee> employee = employeeRepository.findByCpf(cpf);
            if (!hasEmployees()) {
                return new SocketResponse(true, "Sem funcionarios cadastrados.", null);
            }
            if (employee.isPresent()) {
                return new SocketResponse(false, "Busca realizada com sucesso!", employee.get());
            } else {
                return new SocketResponse(true, "Funcionario não encontrado!", null);
            }
        } catch (Exception e) {
            return new SocketResponse(true, "Falha ao buscar por funcionario.", null);
        }
    }

    @Transactional
    public SocketResponse deleteEmployee(String cpf) {
        if (!hasEmployees()) {
            return new SocketResponse(true, "Sem funcionarios cadastrados.", null);
        }
        try {
            employeeRepository.deleteByCpf(cpf);
            return new SocketResponse(false, "Funcionario excluido com sucesso!", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new SocketResponse(true, "Falha ao excluir o funcionario especificado.", null);
        }
    }

    @Transactional
    public SocketResponse updateEmployee(Employee employee) {
        try {
            Optional<Employee> result = employeeRepository.findByCpf(employee.getCpf());
            if (result.isEmpty()) {
                return new SocketResponse(true, "Funcionario não encontrado.", null);
            }

            Employee resultEmployee = result.get();
            resultEmployee.setName(employee.getName());
            resultEmployee.setAddress(employee.getAddress());
            resultEmployee.setRole(employee.getRole());
            resultEmployee.setSalary(employee.getSalary());
            People savedEmployee = employeeRepository.save(resultEmployee);
            return new SocketResponse(false, "Funcionario atualizado com sucesso!", savedEmployee);
        } catch (Exception e) {
            return new SocketResponse(true, "Falha ao atualizar funcionario.", null);
        }

    }

    public boolean hasEmployees() {
        return !(employeeRepository.findAll().size() == 0);
    }
}
