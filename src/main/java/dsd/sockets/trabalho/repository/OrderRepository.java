package dsd.sockets.trabalho.repository;

import dsd.sockets.trabalho.model.Order;
import dsd.sockets.trabalho.model.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM dsd.order WHERE code = :code")
    Optional<Order> findByCode(@Param("code") String code);

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM dsd.order WHERE code = :code")
    void deleteByCode(@Param("code") String code);
}
