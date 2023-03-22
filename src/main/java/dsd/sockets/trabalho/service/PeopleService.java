package dsd.sockets.trabalho.service;

import dsd.sockets.trabalho.model.Customer;
import dsd.sockets.trabalho.model.People;
import dsd.sockets.trabalho.model.SocketResponse;
import dsd.sockets.trabalho.repository.CustomerRepository;
import dsd.sockets.trabalho.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.List;
import java.util.Optional;

@Service
public class PeopleService {
    @Autowired
    PeopleRepository peopleRepository;

    public SocketResponse savePeople(People people) {
        try {
            People result = peopleRepository.save(people);
            return new SocketResponse(false, "Pessoa adicionada com sucesso!", result);
        } catch (Exception e) {
            return new SocketResponse(true, "Falha ao adicionar pessoa.", null);
        }
    }

    public SocketResponse findAllPeople() {
        try {
            List<People> peoples = peopleRepository.findAll();
            return new SocketResponse(false, "Busca realizada com sucesso!", peoples);
        } catch (Exception e) {
            return new SocketResponse(true, "Falha ao buscar por pessoas!", null);
        }
    }

    public SocketResponse findPeople(String cpf) {
        try {
            Optional<People> people = peopleRepository.findPeopleByCpf(cpf);
            if (!hasPeoples()) {
                return new SocketResponse(true, "Sem pessoas cadastradas.", null);
            }
            if (people.isPresent()) {
                return new SocketResponse(false, "Busca realizada com sucesso!", people.get());
            } else {
                return new SocketResponse(true, "Pessoa não encontrada!", null);
            }
        } catch (Exception e) {
            return new SocketResponse(true, "Falha ao buscar por pessoa.", null);
        }
    }

    public SocketResponse deletePeople(String cpf) {
        if (!hasPeoples()) {
            return new SocketResponse(true, "Sem pessoas cadastradas.", null);
        }
        try {
            peopleRepository.deletePeopleByCpf(cpf);
            return new SocketResponse(false, "Pessoa excluida com sucesso!", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new SocketResponse(true, "Falha ao excluir a pessoa especificada.", null);
        }
    }

    public boolean hasPeoples() {
        return !(peopleRepository.findAll().size() == 0);
    }
}
