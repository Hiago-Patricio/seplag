package gov.mt.seplag.gestao_servidores.repository;

import gov.mt.seplag.gestao_servidores.entity.ServidorEfetivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServidorEfetivoRepository extends JpaRepository<ServidorEfetivo, Long> {
}