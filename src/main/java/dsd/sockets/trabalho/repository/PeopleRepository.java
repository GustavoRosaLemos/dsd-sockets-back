package dsd.sockets.trabalho.repository;

import dsd.sockets.trabalho.model.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PeopleRepository extends JpaRepository<People, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM dsd.people WHERE cpf = :cpf")
    Optional<People> findPeopleByCpf(@Param("cpf") String cpf);

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM dsd.people WHERE cpf = :cpf")
    void deletePeopleByCpf(@Param("cpf") String cpf);
}
