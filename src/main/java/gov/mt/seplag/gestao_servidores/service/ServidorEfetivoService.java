package gov.mt.seplag.gestao_servidores.service;

import gov.mt.seplag.gestao_servidores.dto.servidor_efetivo.ServidorEfeitivoByUnidadeResponseDTO;
import gov.mt.seplag.gestao_servidores.dto.servidor_efetivo.ServidorEfetivoRequestDTO;
import gov.mt.seplag.gestao_servidores.dto.servidor_efetivo.ServidorEfetivoResponseDTO;
import gov.mt.seplag.gestao_servidores.entity.Lotacao;
import gov.mt.seplag.gestao_servidores.entity.Pessoa;
import gov.mt.seplag.gestao_servidores.entity.ServidorEfetivo;
import gov.mt.seplag.gestao_servidores.entity.Unidade;
import gov.mt.seplag.gestao_servidores.exception.servidor_efetivo.ServidorEfetivoNotFoundException;
import gov.mt.seplag.gestao_servidores.exception.servidor_temporario.ServidorTemporarioNotFoundException;
import gov.mt.seplag.gestao_servidores.mapper.LotacaoMapper;
import gov.mt.seplag.gestao_servidores.mapper.ServidorEfetivoMapper;
import gov.mt.seplag.gestao_servidores.mapper.UnidadeMapper;
import gov.mt.seplag.gestao_servidores.repository.ServidorEfetivoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ServidorEfetivoService {

    private final ServidorEfetivoRepository servidorEfetivoRepository;
    private final ServidorEfetivoMapper servidorEfetivoMapper;
    private final PessoaService pessoaService;
    private final MinIOService minIOService;
    private final UnidadeService unidadeService;
    private final LotacaoService lotacaoService;
    private final UnidadeMapper unidadeMapper;
    private final LotacaoMapper lotacaoMapper;

    public Page<ServidorEfetivoResponseDTO> findAll(Pageable pageable) {
        log.info("Buscando todos os servidores efetivos.");
        Page<ServidorEfetivo> page = servidorEfetivoRepository.findAll(pageable);

        return page.map(servidorEfetivo -> {
            ServidorEfetivoResponseDTO dto = servidorEfetivoMapper.toServidorEfetivoResponseDTO(servidorEfetivo);
            dto.setLotacoes(
                    servidorEfetivo.getPessoa().getLotacoes().stream()
                            .map(lotacaoMapper::toLotacaoResponseDTO)
                            .collect(Collectors.toList())
            );
            return dto;
        });
    }

    public ServidorEfetivoResponseDTO findById(Long id) {
        log.info("Buscando servidor efetivo com ID {}", id);
        ServidorEfetivo servidorEfetivo = servidorEfetivoRepository.findById(id)
                .orElseThrow(() -> new ServidorEfetivoNotFoundException(id));

        ServidorEfetivoResponseDTO dto = servidorEfetivoMapper.toServidorEfetivoResponseDTO(servidorEfetivo);
        dto.setLotacoes(
                servidorEfetivo.getPessoa().getLotacoes().stream()
                        .map(lotacaoMapper::toLotacaoResponseDTO)
                        .collect(Collectors.toList())
        );

        return dto;
    }


    public List<ServidorEfeitivoByUnidadeResponseDTO> findByUnidadeId(Long id, Pageable pageable) {
        log.info("Buscando servidores efetivos da unidade com ID {}", id);
        Unidade unidade = unidadeMapper.toUnidadeEntity(unidadeService.findUnidadeById(id));
        List<Lotacao> locatoes = lotacaoService.findByUnidadeId(unidade);
        List<ServidorEfetivo> servidores = new ArrayList<>();

        for (Lotacao lotacao : locatoes) {
            servidores.add(lotacao.getPessoa().getServidoresEfetivos().getFirst());
        }

        List<ServidorEfeitivoByUnidadeResponseDTO> servidoresEfetivos = new ArrayList<>();

        for (ServidorEfetivo servidorEfetivo : servidores) {
            Pessoa pessoa = servidorEfetivo.getPessoa();

            ServidorEfeitivoByUnidadeResponseDTO servidorEfetivoDTO = new ServidorEfeitivoByUnidadeResponseDTO();
            servidorEfetivoDTO.setUnidade(pessoa.getLotacoes().getFirst().getUnidade().getNome());
            servidorEfetivoDTO.setNome(pessoa.getNome());

            if (!pessoa.getFotosPessoa().isEmpty()) {
                var primeiraFoto = pessoa.getFotosPessoa().getFirst().getHash();
                var urlTemporaria = minIOService.gerarLinkTemporario(primeiraFoto);
                servidorEfetivoDTO.setUrlTemporaria(urlTemporaria);
            }

            // Converter Date para LocalDate
            Date dataNascimento = pessoa.getDataNascimento();
            if (dataNascimento != null) {
                LocalDate nascimento = dataNascimento.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();
                int idade = Period.between(nascimento, LocalDate.now()).getYears();
                servidorEfetivoDTO.setIdade(idade);
            } else {
                servidorEfetivoDTO.setIdade(null);
            }

            servidoresEfetivos.add(servidorEfetivoDTO);
        }

        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), servidoresEfetivos.size());

        if (start > end) {
            return Collections.emptyList();
        }

        return servidoresEfetivos.subList(start, end);
    }

    public ServidorEfetivoResponseDTO createServidorEfetivo(ServidorEfetivoRequestDTO requestDTO) {
        log.info("Criando servidor efetivo: {}", requestDTO);
        ServidorEfetivo servidorEfetivo = servidorEfetivoMapper.toServidorEfetivoEntity(requestDTO);

        var pessoa = pessoaService.createPessoa(requestDTO.getPessoa());
        servidorEfetivo.setPessoa(pessoa);
        servidorEfetivo.setPessoaId(pessoa.getId());

        ServidorEfetivo savedServidorEfetivo = servidorEfetivoRepository.save(servidorEfetivo);

        return servidorEfetivoMapper.toServidorEfetivoResponseDTO(savedServidorEfetivo);
    }


    public ServidorEfetivoResponseDTO updateServidorEfetivo(Long id, ServidorEfetivoRequestDTO requestDTO) {
        log.info("Criando servidor efetivo: {}", requestDTO);
        ServidorEfetivo existingServidorEfetivo = servidorEfetivoRepository.findById(id)
                .orElseThrow(() -> new ServidorEfetivoNotFoundException(id));

        // Busca ou valida a pessoa associada
        var pessoa = pessoaService.createPessoa(requestDTO.getPessoa());

        servidorEfetivoMapper.updateServidorEfetivoFromDTO(requestDTO, existingServidorEfetivo, pessoa);

        ServidorEfetivo updatedServidorEfetivo = servidorEfetivoRepository.save(existingServidorEfetivo);

        return servidorEfetivoMapper.toServidorEfetivoResponseDTO(updatedServidorEfetivo);
    }

    public void deleteServidorEfetivo(Long id) {
        log.info("Deletando servidor efetivo com ID {}", id);
        Optional<ServidorEfetivo> servidorTemporario = servidorEfetivoRepository.findById(id);

        if (!servidorTemporario.isPresent()) {
            log.error("Servidor efetivo com ID {} não encontrado.", id);
            throw new ServidorTemporarioNotFoundException(id);
        }

        servidorEfetivoRepository.deleteById(id);
        pessoaService.deletePessoa(servidorTemporario.get().getPessoa().getId());
    }
}