package gov.mt.seplag.gestao_servidores.repository;

import gov.mt.seplag.gestao_servidores.entity.Unidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadeRepository extends JpaRepository<Unidade, Long> {
}