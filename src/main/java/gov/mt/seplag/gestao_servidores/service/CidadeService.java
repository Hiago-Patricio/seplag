package gov.mt.seplag.gestao_servidores.service;

import gov.mt.seplag.gestao_servidores.dto.cidade.CidadeResponseDTO;
import gov.mt.seplag.gestao_servidores.dto.cidade.CidadeRequestDTO;
import gov.mt.seplag.gestao_servidores.entity.Cidade;
import gov.mt.seplag.gestao_servidores.exception.cidade.CidadeNotFoundException;
import gov.mt.seplag.gestao_servidores.mapper.CidadeMapper;
import gov.mt.seplag.gestao_servidores.repository.CidadeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CidadeService {

    private final CidadeRepository repository;
    private final CidadeMapper mapper;

    public Page<CidadeResponseDTO> findAll(Pageable pageable) {
        log.info("Buscando todas as cidades.");

        Page<Cidade> page = repository.findAll(pageable);
        Page<CidadeResponseDTO> dtoPage = page.map(mapper::toCidadeDTO);

        log.debug("{} cidades localizadas.", page.getTotalElements());
        return dtoPage;
    }

    public CidadeResponseDTO findCidadeById(Long id) {
        log.info("Buscando cidade com ID: {}", id);
        Optional<Cidade> cidadeOpt = repository.findById(id);

        if (cidadeOpt.isPresent()) {
            CidadeResponseDTO cidadeResponseDTO = mapper.toCidadeDTO(cidadeOpt.get());
            log.debug("Cidade encontrada: {}", cidadeResponseDTO);
            return cidadeResponseDTO;
        } else {
            log.warn("Cidade com ID {} não encontrada.", id);
            throw new CidadeNotFoundException(id);
        }
    }

    public CidadeResponseDTO createCidade(CidadeRequestDTO dto) {
        log.info("Criando cidade: {}", dto);

        Optional<Cidade> cidade = repository.findByNomeAndUf(dto.getNome(), dto.getUf());

        if (cidade.isPresent()) {
            log.debug("Cidade já existe com ID: {}", cidade.get().getId());
            return mapper.toCidadeDTO(cidade.get());
        } else {
            Cidade savedCidade = repository.save(mapper.toEntity(dto));
            log.debug("Cidade criada com ID: {}", savedCidade.getId());
            return mapper.toCidadeDTO(savedCidade);
        }
    }

    public CidadeResponseDTO updateCidade(Long id, CidadeRequestDTO cidadeDTO) {
        log.info("Atualizando cidade com ID: {} - {}", id, cidadeDTO);

        Cidade existingCidade = repository.findById(id)
                .orElseThrow(() -> new CidadeNotFoundException(id));

        mapper.updateEntityFromDTO(cidadeDTO, existingCidade);

        Cidade updatedCidade = repository.save(existingCidade);
        log.debug("Cidade autalizada: {}", updatedCidade);

        return mapper.toCidadeDTO(updatedCidade);
    }

    public void deleteCidade(Long id) {
        log.info("Apagando cidade com ID: {}", id);
        if (repository.existsById(id)) {
            repository.deleteById(id);
            log.debug("Cidade com ID {} deletada com sucesso.", id);
        } else {
            log.error("Cidade com ID {} não localizada.", id);
            throw new CidadeNotFoundException(id);
        }
    }
}
