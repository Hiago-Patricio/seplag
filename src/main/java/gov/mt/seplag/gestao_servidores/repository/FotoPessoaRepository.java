package gov.mt.seplag.gestao_servidores.repository;

import gov.mt.seplag.gestao_servidores.entity.FotoPessoa;
import gov.mt.seplag.gestao_servidores.entity.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FotoPessoaRepository extends JpaRepository<FotoPessoa, Long> {
    Page<FotoPessoa> findFotoPessoaByPessoa(Pessoa pessoa, Pageable pageable);
    List<FotoPessoa> findFotoPessoaByPessoa(Pessoa pessoa);
}
