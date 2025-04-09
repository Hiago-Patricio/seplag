package gov.mt.seplag.gestao_servidores.service;

import gov.mt.seplag.gestao_servidores.dto.pessoa.PessoaResponseDTO;
import gov.mt.seplag.gestao_servidores.dto.pessoa.PessoaRequestDTO;
import gov.mt.seplag.gestao_servidores.entity.Pessoa;
import gov.mt.seplag.gestao_servidores.exception.pessoa.PessoaNotFoundException;
import gov.mt.seplag.gestao_servidores.mapper.PessoaMapper;
import gov.mt.seplag.gestao_servidores.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository pessoaRepository;
    private final PessoaMapper pessoaMapper;

    public Page<PessoaResponseDTO> findAll(Pageable pageable) {
        log.info("Buscando todas as pessoas.");
        
        Page<Pessoa> page = pessoaRepository.findAll(pageable);
        Page<PessoaResponseDTO> dtoPage = page.map(pessoaMapper::toPessoaResponseDTO);

        log.debug("{} pessoas localizadas.", page.getTotalElements());
        return dtoPage;
    }

    public PessoaResponseDTO findPessoaById(Long id) {
        log.info("Buscando pessoa com ID: {}", id);
        Optional<Pessoa> pessoaOpt = pessoaRepository.findById(id);

        if (pessoaOpt.isPresent()) {
            PessoaResponseDTO pessoaResponseDTO = pessoaMapper.toPessoaResponseDTO(pessoaOpt.get());
            log.debug("Pessoa encontrada: {}", pessoaResponseDTO);
            return pessoaResponseDTO;
        } else {
            log.warn("Pessoa com ID {} não encontrada.", id);
            throw new PessoaNotFoundException(id);
        }
    }

    public PessoaResponseDTO createPessoa(PessoaRequestDTO pessoaRequestDTO) {
        log.info("Criando pessoa: {}", pessoaRequestDTO);
        Pessoa pessoaEntity = pessoaMapper.toPessoaEntity(pessoaRequestDTO);
        Pessoa savedPessoa = pessoaRepository.save(pessoaEntity);
        log.debug("Pessoa criada com ID: {}", savedPessoa.getId());
        return pessoaMapper.toPessoaResponseDTO(savedPessoa);
    }

    public PessoaResponseDTO updatePessoa(Long id, PessoaRequestDTO pessoaRequestDTO) {
        log.info("Atualizando pessoa com ID: {} - {}", id, pessoaRequestDTO);

        Pessoa existingPessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new PessoaNotFoundException(id));

        pessoaMapper.updatePessoaFromDTO(pessoaRequestDTO, existingPessoa);

        Pessoa updatedPessoa = pessoaRepository.save(existingPessoa);
        log.debug("Pessoa atualizada com sucesso: {}", updatedPessoa);

        return pessoaMapper.toPessoaResponseDTO(updatedPessoa);
    }

    public void deletePessoa(Long id) {
        log.info("Apagando pessoa com ID: {}", id);
        if (pessoaRepository.existsById(id)) {
            pessoaRepository.deleteById(id);
            log.debug("Pessoa com ID {} deletada com sucesso.", id);
        } else {
            log.error("Pessoa com ID {} não localizada.", id);
            throw new PessoaNotFoundException(id);
        }
    }
}