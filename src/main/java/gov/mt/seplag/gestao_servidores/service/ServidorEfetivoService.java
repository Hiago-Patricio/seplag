package gov.mt.seplag.gestao_servidores.service;

import gov.mt.seplag.gestao_servidores.dto.servidor_efetivo.ServidorEfetivoRequestDTO;
import gov.mt.seplag.gestao_servidores.dto.servidor_efetivo.ServidorEfetivoResponseDTO;
import gov.mt.seplag.gestao_servidores.entity.Pessoa;
import gov.mt.seplag.gestao_servidores.entity.ServidorEfetivo;
import gov.mt.seplag.gestao_servidores.exception.pessoa.PessoaNotFoundException;
import gov.mt.seplag.gestao_servidores.exception.servidor_efetivo.ServidorEfetivoNotFoundException;
import gov.mt.seplag.gestao_servidores.mapper.ServidorEfetivoMapper;
import gov.mt.seplag.gestao_servidores.repository.PessoaRepository;
import gov.mt.seplag.gestao_servidores.repository.ServidorEfetivoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ServidorEfetivoService {

    private final ServidorEfetivoRepository servidorEfetivoRepository;
    private final PessoaRepository pessoaRepository;
    private final ServidorEfetivoMapper servidorEfetivoMapper;

    public Page<ServidorEfetivoResponseDTO> findAll(Pageable pageable) {
        log.info("Buscando todos os servidores efetivos.");
        Page<ServidorEfetivo> page = servidorEfetivoRepository.findAll(pageable);
        return page.map(servidorEfetivoMapper::toServidorEfetivoResponseDTO);
    }

    public ServidorEfetivoResponseDTO findById(Long id) {
        log.info("Buscando servidor efetivo com ID {}", id);
        ServidorEfetivo servidorEfetivo = servidorEfetivoRepository.findById(id)
                .orElseThrow(() -> new ServidorEfetivoNotFoundException(id));
        return servidorEfetivoMapper.toServidorEfetivoResponseDTO(servidorEfetivo);
    }

    public ServidorEfetivoResponseDTO createServidorEfetivo(ServidorEfetivoRequestDTO requestDTO) {
        log.info("Criando servidor efetivo: {}", requestDTO);

        Pessoa pessoa = pessoaRepository.findById(requestDTO.getPessoaId())
                .orElseThrow(() -> new PessoaNotFoundException(requestDTO.getPessoaId()));

        ServidorEfetivo servidorEfetivo = servidorEfetivoMapper.toServidorEfetivoEntity(requestDTO, pessoa);
        servidorEfetivo = servidorEfetivoRepository.save(servidorEfetivo);

        return servidorEfetivoMapper.toServidorEfetivoResponseDTO(servidorEfetivo);
    }

    public ServidorEfetivoResponseDTO updateServidorEfetivo(Long id, ServidorEfetivoRequestDTO requestDTO) {
        log.info("Atualizando servidor efetivo com ID {}: {}", id, requestDTO);

        ServidorEfetivo servidorEfetivo = servidorEfetivoRepository.findById(id)
                .orElseThrow(() -> new ServidorEfetivoNotFoundException(id));

        Pessoa pessoa = pessoaRepository.findById(requestDTO.getPessoaId())
                .orElseThrow(() -> new PessoaNotFoundException(requestDTO.getPessoaId()));

        servidorEfetivoMapper.updateServidorEfetivoFromDTO(requestDTO, servidorEfetivo, pessoa);
        servidorEfetivo = servidorEfetivoRepository.save(servidorEfetivo);

        return servidorEfetivoMapper.toServidorEfetivoResponseDTO(servidorEfetivo);
    }

    public void deleteServidorEfetivo(Long id) {
        log.info("Deletando servidor efetivo com ID {}", id);
        if (!servidorEfetivoRepository.existsById(id)) {
            log.error("Servidor efetivo com ID {} não encontrado.", id);
            throw new ServidorEfetivoNotFoundException(id);
        }
        servidorEfetivoRepository.deleteById(id);
    }
}