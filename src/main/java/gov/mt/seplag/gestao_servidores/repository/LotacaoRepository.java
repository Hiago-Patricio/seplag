package gov.mt.seplag.gestao_servidores.repository;

import gov.mt.seplag.gestao_servidores.dto.lotacao.LotacaoResponseDTO;
import gov.mt.seplag.gestao_servidores.entity.Lotacao;
import gov.mt.seplag.gestao_servidores.entity.Unidade;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LotacaoRepository extends JpaRepository<Lotacao, Long> {
    List<Lotacao> findByUnidade(Unidade unidade);
}