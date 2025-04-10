package gov.mt.seplag.gestao_servidores.service;

import gov.mt.seplag.gestao_servidores.dto.pessoa.PessoaRequestDTO;
import gov.mt.seplag.gestao_servidores.dto.servidor_temporario.ServidorTemporarioRequestDTO;
import gov.mt.seplag.gestao_servidores.dto.servidor_temporario.ServidorTemporarioResponseDTO;
import gov.mt.seplag.gestao_servidores.entity.Lotacao;
import gov.mt.seplag.gestao_servidores.entity.Pessoa;
import gov.mt.seplag.gestao_servidores.entity.ServidorTemporario;
import gov.mt.seplag.gestao_servidores.exception.pessoa.PessoaNotFoundException;
import gov.mt.seplag.gestao_servidores.exception.servidor_temporario.ServidorTemporarioNotFoundException;
import gov.mt.seplag.gestao_servidores.mapper.LotacaoMapper;
import gov.mt.seplag.gestao_servidores.mapper.ServidorTemporarioMapper;
import gov.mt.seplag.gestao_servidores.repository.PessoaRepository;
import gov.mt.seplag.gestao_servidores.repository.ServidorTemporarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ServidorTemporarioService {

    private final ServidorTemporarioRepository servidorTemporarioRepository;
    private final ServidorTemporarioMapper servidorTemporarioMapper;
    private final LotacaoMapper lotacaoMapper;
    private final PessoaService pessoaService;

    public Page<ServidorTemporarioResponseDTO> findAll(Pageable pageable) {
        log.info("Buscando todos os servidores temporários.");
        Page<ServidorTemporario> page = servidorTemporarioRepository.findAll(pageable);

        return page.map(servidorTemporario -> {
            ServidorTemporarioResponseDTO dto = servidorTemporarioMapper.toServidorTemporarioResponseDTO(servidorTemporario);
            dto.setLotacoes(
                    servidorTemporario.getPessoa().getLotacoes().stream()
                            .map(lotacaoMapper::toLotacaoResponseDTO)
                            .collect(Collectors.toList())
            );
            return dto;
        });
    }

    public ServidorTemporarioResponseDTO findById(Long id) {
        log.info("Buscando servidor temporário com ID {}", id);
        ServidorTemporario servidorTemporario = servidorTemporarioRepository.findById(id)
                .orElseThrow(() -> new ServidorTemporarioNotFoundException(id));

        ServidorTemporarioResponseDTO dto = servidorTemporarioMapper.toServidorTemporarioResponseDTO(servidorTemporario);
        dto.setLotacoes(
                servidorTemporario.getPessoa().getLotacoes().stream()
                        .map(lotacaoMapper::toLotacaoResponseDTO)
                        .collect(Collectors.toList())
        );

        return dto;
    }

    public ServidorTemporarioResponseDTO createServidorTemporario(ServidorTemporarioRequestDTO requestDTO) {
        log.info("Criando servidor temporário: {}", requestDTO);
        ServidorTemporario servidorTemporario = servidorTemporarioMapper.toServidorTemporarioEntity(requestDTO);

        Pessoa pessoa = pessoaService.createPessoa(requestDTO.getPessoa());
        servidorTemporario.setPessoa(pessoa);
        servidorTemporario.setPessoaId(pessoa.getId());

        ServidorTemporario savedServidorTemporario = servidorTemporarioRepository.save(servidorTemporario);

        return servidorTemporarioMapper.toServidorTemporarioResponseDTO(savedServidorTemporario);
    }

    public ServidorTemporarioResponseDTO updateServidorTemporario(Long id, ServidorTemporarioRequestDTO requestDTO) {
        log.info("Atualizando servidor temporário com ID {}: {}", id, requestDTO);

        ServidorTemporario existingServidorTemporario = servidorTemporarioRepository.findById(id)
                .orElseThrow(() -> new ServidorTemporarioNotFoundException(id));

        PessoaRequestDTO pessoaRequestDTO = requestDTO.getPessoa();
        Pessoa pessoa = pessoaService.updatePessoa(id, pessoaRequestDTO);

        servidorTemporarioMapper.updateServidorTemporarioFromDTO(requestDTO, existingServidorTemporario, pessoa);
        ServidorTemporario updatedServidorTemporario = servidorTemporarioRepository.save(existingServidorTemporario);

        return servidorTemporarioMapper.toServidorTemporarioResponseDTO(updatedServidorTemporario);
    }

    public void deleteServidorTemporario(Long id) {
        log.info("Deletando servidor temporário com ID {}", id);
        Optional<ServidorTemporario> servidorTemporario = servidorTemporarioRepository.findById(id);

        if (!servidorTemporario.isPresent()) {
            log.error("Servidor temporário com ID {} não encontrado.", id);
            throw new ServidorTemporarioNotFoundException(id);
        }

        servidorTemporarioRepository.deleteById(id);
        pessoaService.deletePessoa(servidorTemporario.get().getPessoa().getId());
    }
}