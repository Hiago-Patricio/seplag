package gov.mt.seplag.gestao_servidores.service;

import gov.mt.seplag.gestao_servidores.dto.lotacao.LotacaoRequestDTO;
import gov.mt.seplag.gestao_servidores.dto.lotacao.LotacaoResponseDTO;
import gov.mt.seplag.gestao_servidores.entity.Lotacao;
import gov.mt.seplag.gestao_servidores.exception.lotacao.LotacaoNotFoundException;
import gov.mt.seplag.gestao_servidores.mapper.LotacaoMapper;
import gov.mt.seplag.gestao_servidores.repository.LotacaoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LotacaoService {

    private final LotacaoRepository lotacaoRepository;
    private final LotacaoMapper lotacaoMapper;

    public Page<LotacaoResponseDTO> findAll(Pageable pageable) {
        log.info("Buscando todas as lotações.");
        Page<Lotacao> page = lotacaoRepository.findAll(pageable);
        return page.map(lotacaoMapper::toLotacaoResponseDTO);
    }

    public LotacaoResponseDTO findById(Long id) {
        log.info("Buscando lotação com ID {}", id);
        Lotacao lotacao = lotacaoRepository.findById(id)
                .orElseThrow(() -> new LotacaoNotFoundException(id));
        return lotacaoMapper.toLotacaoResponseDTO(lotacao);
    }

    public LotacaoResponseDTO createLotacao(LotacaoRequestDTO requestDTO) {
        log.info("Criando lotação: {}", requestDTO);
        Lotacao lotacao = lotacaoMapper.toLotacaoEntity(requestDTO);
        lotacao = lotacaoRepository.save(lotacao);
        return lotacaoMapper.toLotacaoResponseDTO(lotacao);
    }

    public LotacaoResponseDTO updateLotacao(Long id, LotacaoRequestDTO requestDTO) {
        log.info("Atualizando lotação com ID {}: {}", id, requestDTO);
        Lotacao lotacao = lotacaoRepository.findById(id)
                .orElseThrow(() -> new LotacaoNotFoundException(id));
        lotacaoMapper.updateLotacaoFromDTO(requestDTO, lotacao);
        lotacao = lotacaoRepository.save(lotacao);
        return lotacaoMapper.toLotacaoResponseDTO(lotacao);
    }

    public void deleteLotacao(Long id) {
        log.info("Deletando lotação com ID {}", id);
        if (!lotacaoRepository.existsById(id)) {
            log.error("Lotação com ID {} não encontrada.", id);
            throw new LotacaoNotFoundException(id);
        }
        lotacaoRepository.deleteById(id);
    }
}