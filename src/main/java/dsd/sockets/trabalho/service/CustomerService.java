package dsd.sockets.trabalho.service;

import dsd.sockets.trabalho.model.Customer;
import dsd.sockets.trabalho.model.People;
import dsd.sockets.trabalho.model.SocketResponse;
import dsd.sockets.trabalho.model.dto.CustomerDTO;
import dsd.sockets.trabalho.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public SocketResponse findAllCustomers() {
        try {
            List<Customer> peoples = customerRepository.findAll();
            List<CustomerDTO> customerDTOS = peoples.stream().map(customer -> new CustomerDTO(
                    customer.getCpf(),
                    customer.getName(),
                    customer.getAddress(),
                    customer.getScore(),
                    customer.getBonus()
            )).toList();
            return new SocketResponse(false, "Busca realizada com sucesso!", customerDTOS);
        } catch (Exception e) {
            return new SocketResponse(true, "Falha ao buscar por clientes!", null);
        }
    }

    public SocketResponse saveCustomer(Customer customer) {
        try {
            People result = customerRepository.save(customer);
            return new SocketResponse(false, "Cliente adicionado com sucesso!", result);
        } catch (Exception e) {
            return new SocketResponse(true, "Falha ao adicionar cliente.", null);
        }
    }

    public SocketResponse findCustomer(String cpf) {
        try {
            Optional<Customer> customer = customerRepository.findByCpf(cpf);
            if (!hasCustomers()) {
                return new SocketResponse(true, "Sem clientes cadastrados.", null);
            }
            if (customer.isPresent()) {
                Customer resultCustomer = customer.get();
                return new SocketResponse(false, "Busca realizada com sucesso!", new CustomerDTO(
                        resultCustomer.getCpf(),
                        resultCustomer.getName(),
                        resultCustomer.getAddress(),
                        resultCustomer.getScore(),
                        resultCustomer.getBonus()
                ));
            } else {
                return new SocketResponse(true, "Cliente não encontrado!", null);
            }
        } catch (Exception e) {
            return new SocketResponse(true, "Falha ao buscar por cliente.", null);
        }
    }

    @Transactional
    public SocketResponse deleteCustomer(String cpf) {
        if (!hasCustomers()) {
            return new SocketResponse(true, "Sem clientes cadastrados.", null);
        }
        try {
            customerRepository.deleteByCpf(cpf);
            return new SocketResponse(false, "Cliente excluido com sucesso!", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new SocketResponse(true, "Falha ao excluir o cliente especificado.", null);
        }
    }

    @Transactional
    public SocketResponse updateCustomer(CustomerDTO customer) {
        try {
            Optional<Customer> result = customerRepository.findByCpf(customer.getCpf());
            if (result.isEmpty()) {
                return new SocketResponse(true, "Pessoa não enontrada.", null);
            }

            Customer resultCustomer = result.get();
            resultCustomer.setName(customer.getName());
            resultCustomer.setAddress(customer.getAddress());
            resultCustomer.setBonus(customer.getBonus());
            resultCustomer.setScore(customer.getScore());
            Customer savedCustomer = customerRepository.save(resultCustomer);
            return new SocketResponse(false, "Cliente atualizado com sucesso!", new CustomerDTO(
                    savedCustomer.getCpf(),
                    savedCustomer.getName(),
                    savedCustomer.getAddress(),
                    savedCustomer.getScore(),
                    savedCustomer.getBonus()
            ));
        } catch (Exception e) {
            return new SocketResponse(true, "Falha ao atualizar o cliente.", null);
        }

    }
    public boolean hasCustomers() {
        return !(customerRepository.findAll().size() == 0);
    }
}
