package gov.mt.seplag.gestao_servidores.service;

import gov.mt.seplag.gestao_servidores.dto.endereco.EnderecoResponseDTO;
import gov.mt.seplag.gestao_servidores.dto.lotacao.LotacaoRequestDTO;
import gov.mt.seplag.gestao_servidores.dto.lotacao.LotacaoResponseDTO;
import gov.mt.seplag.gestao_servidores.entity.*;
import gov.mt.seplag.gestao_servidores.exception.lotacao.LotacaoNotFoundException;
import gov.mt.seplag.gestao_servidores.mapper.EnderecoMapper;
import gov.mt.seplag.gestao_servidores.mapper.LotacaoMapper;
import gov.mt.seplag.gestao_servidores.mapper.UnidadeMapper;
import gov.mt.seplag.gestao_servidores.repository.LotacaoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LotacaoService {

    private final LotacaoRepository lotacaoRepository;
    private final LotacaoMapper lotacaoMapper;
    private final PessoaService pessoaService;
    private final UnidadeService unidadeService;
    private final UnidadeMapper unidadeMapper;
    private final EnderecoMapper enderecoMapper;

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

    public List<Lotacao> findByUnidadeId(Unidade unidade) {
        log.info("Buscando lotação com unidade {}", unidade);
        List<Lotacao> lotacoes = lotacaoRepository.findByUnidade(unidade);
        return lotacoes;
    }

    public List<EnderecoResponseDTO> findByServidorEfetivoNome(String servidorEfetivoNome, Pageable pageable) {
        log.info("Buscando lotação com servidorEfetivoNome {}", servidorEfetivoNome);

        List<Pessoa> pessoas = pessoaService.findPessoasByNome(servidorEfetivoNome);
        List<Pessoa> servidoresEfetivos = new ArrayList<>();

        for (Pessoa pessoa : pessoas) {
            if (!pessoa.getServidoresEfetivos().isEmpty())
                servidoresEfetivos.add(pessoa);
        }

        List<Unidade> unidades = servidoresEfetivos.stream()
                .flatMap(pessoa -> pessoa.getLotacoes().stream())
                .map(Lotacao::getUnidade)
                .distinct()
                .toList();

        List<EnderecoResponseDTO> enderecos = unidades.stream()
                .flatMap(unidade -> unidade.getEnderecos().stream())
                .map(enderecoMapper::toEnderecoResponseDTO)
                .toList();

        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), enderecos.size());
        List<EnderecoResponseDTO> pagina = enderecos.subList(start, end);

        return pagina;
    }

    public LotacaoResponseDTO createLotacao(LotacaoRequestDTO requestDTO) {
        log.info("Criando lotação: {}", requestDTO);
        Lotacao lotacao = lotacaoMapper.toLotacaoEntity(requestDTO);
        Pessoa pessoa = pessoaService.findPessoaById(requestDTO.getPessoaId());
        Unidade unidade = unidadeMapper.toUnidadeEntity(unidadeService.findUnidadeById(requestDTO.getUnidadeId()));

        lotacao.setPessoa(pessoa);
        lotacao.setUnidade(unidade);

        lotacao = lotacaoRepository.save(lotacao);
        return lotacaoMapper.toLotacaoResponseDTO(lotacao);
    }

    public LotacaoResponseDTO updateLotacao(Long id, LotacaoRequestDTO requestDTO) {
        log.info("Atualizando lotação com ID {}: {}", id, requestDTO);
        Lotacao lotacao = lotacaoRepository.findById(id)
                .orElseThrow(() -> new LotacaoNotFoundException(id));
        lotacaoMapper.updateLotacaoFromDTO(requestDTO, lotacao);

        Pessoa pessoa = pessoaService.findPessoaById(requestDTO.getPessoaId());
        Unidade unidade = unidadeMapper.toUnidadeEntity(unidadeService.findUnidadeById(requestDTO.getUnidadeId()));

        lotacao.setPessoa(pessoa);
        lotacao.setUnidade(unidade);

        Lotacao updatedLotacao = lotacaoRepository.save(lotacao);
        return lotacaoMapper.toLotacaoResponseDTO(updatedLotacao);
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