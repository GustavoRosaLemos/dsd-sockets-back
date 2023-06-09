package dsd.sockets.trabalho.service;

import dsd.sockets.trabalho.model.People;
import dsd.sockets.trabalho.model.SocketResponse;
import dsd.sockets.trabalho.model.dto.PeopleDTO;
import dsd.sockets.trabalho.repository.PeopleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeopleService {
    @Autowired
    PeopleRepository peopleRepository;

    @Transactional
    public SocketResponse savePeople(People people) {
        try {
            People result = peopleRepository.save(people);
            return new SocketResponse(false, "Pessoa adicionada com sucesso!", new PeopleDTO(
                    result.getCpf(),
                    result.getName(),
                    result.getAddress()
            ));
        } catch (Exception e) {
            return new SocketResponse(true, "Falha ao adicionar pessoa.", null);
        }
    }

    public SocketResponse findAllPeople() {
        try {
            List<People> peoples = peopleRepository.findAll();
            List<PeopleDTO> peopleDTOS = peoples.stream().map(people -> new PeopleDTO(
                    people.getCpf(),
                    people.getName(),
                    people.getAddress()
            )).toList();
            return new SocketResponse(false, "Busca realizada com sucesso!", peopleDTOS);
        } catch (Exception e) {
            return new SocketResponse(true, "Falha ao buscar por pessoas!", null);
        }
    }

    public SocketResponse findPeople(String cpf) {
        try {
            Optional<People> people = peopleRepository.findByCpf(cpf);
            if (!hasPeoples()) {
                return new SocketResponse(true, "Sem pessoas cadastradas.", null);
            }
            if (people.isPresent()) {
                People resultPeople = people.get();
                return new SocketResponse(false, "Busca realizada com sucesso!", new PeopleDTO(
                        resultPeople.getCpf(),
                        resultPeople.getName(),
                        resultPeople.getAddress()
                ));
            } else {
                return new SocketResponse(true, "Pessoa não encontrada!", null);
            }
        } catch (Exception e) {
            return new SocketResponse(true, "Falha ao buscar por pessoa.", null);
        }
    }

    @Transactional
    public SocketResponse deletePeople(String cpf) {
        if (!hasPeoples()) {
            return new SocketResponse(true, "Sem pessoas cadastradas.", null);
        }
        try {
            peopleRepository.deleteByCpf(cpf);
            return new SocketResponse(false, "Pessoa excluida com sucesso!", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new SocketResponse(true, "Falha ao excluir a pessoa especificada.", null);
        }
    }

    @Transactional
    public SocketResponse updatePeople(PeopleDTO people) {
        try {
           Optional<People> result = peopleRepository.findByCpf(people.getCpf());
           if (result.isEmpty()) {
               return new SocketResponse(true, "Pessoa não enontrada.", null);
           }

           People resultPeople = result.get();
           resultPeople.setName(people.getName());
           resultPeople.setAddress(people.getAddress());
          People savedPeople = peopleRepository.save(resultPeople);
           return new SocketResponse(false, "Pessoa atualizada com sucesso!", new PeopleDTO(
                   savedPeople.getCpf(),
                   savedPeople.getName(),
                   savedPeople.getAddress()
           ));
        } catch (Exception e) {
            return new SocketResponse(true, "Falha ao atualizar a pessoa.", null);
        }

    }

    public boolean hasPeoples() {
        return !(peopleRepository.findAll().size() == 0);
    }
}
