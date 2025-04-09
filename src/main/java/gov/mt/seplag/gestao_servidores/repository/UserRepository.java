package gov.mt.seplag.gestao_servidores.repository;

import gov.mt.seplag.gestao_servidores.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
