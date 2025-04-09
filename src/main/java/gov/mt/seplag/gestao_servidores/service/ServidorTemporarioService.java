package gov.mt.seplag.gestao_servidores.service;

import gov.mt.seplag.gestao_servidores.dto.servidor_temporario.ServidorTemporarioRequestDTO;
import gov.mt.seplag.gestao_servidores.dto.servidor_temporario.ServidorTemporarioResponseDTO;
import gov.mt.seplag.gestao_servidores.entity.Pessoa;
import gov.mt.seplag.gestao_servidores.entity.ServidorTemporario;
import gov.mt.seplag.gestao_servidores.exception.pessoa.PessoaNotFoundException;
import gov.mt.seplag.gestao_servidores.exception.servidor_temporario.ServidorTemporarioNotFoundException;
import gov.mt.seplag.gestao_servidores.mapper.ServidorTemporarioMapper;
import gov.mt.seplag.gestao_servidores.repository.PessoaRepository;
import gov.mt.seplag.gestao_servidores.repository.ServidorTemporarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ServidorTemporarioService {

    private final ServidorTemporarioRepository servidorTemporarioRepository;
    private final PessoaRepository pessoaRepository;
    private final ServidorTemporarioMapper servidorTemporarioMapper;

    public Page<ServidorTemporarioResponseDTO> findAll(Pageable pageable) {
        log.info("Buscando todos os servidores temporários.");
        Page<ServidorTemporario> page = servidorTemporarioRepository.findAll(pageable);
        return page.map(servidorTemporarioMapper::toServidorTemporarioResponseDTO);
    }

    public ServidorTemporarioResponseDTO findById(Long id) {
        log.info("Buscando servidor temporário com ID {}", id);
        ServidorTemporario servidorTemporario = servidorTemporarioRepository.findById(id)
                .orElseThrow(() -> new ServidorTemporarioNotFoundException(id));
        return servidorTemporarioMapper.toServidorTemporarioResponseDTO(servidorTemporario);
    }

    public ServidorTemporarioResponseDTO createServidorTemporario(ServidorTemporarioRequestDTO requestDTO) {
        log.info("Criando servidor temporário: {}", requestDTO);

        Pessoa pessoa = pessoaRepository.findById(requestDTO.getPessoaId())
                .orElseThrow(() -> new PessoaNotFoundException(requestDTO.getPessoaId()));

        ServidorTemporario servidorTemporario = servidorTemporarioMapper.toServidorTemporarioEntity(requestDTO, pessoa);
        servidorTemporario = servidorTemporarioRepository.save(servidorTemporario);

        return servidorTemporarioMapper.toServidorTemporarioResponseDTO(servidorTemporario);
    }

    public ServidorTemporarioResponseDTO updateServidorTemporario(Long id, ServidorTemporarioRequestDTO requestDTO) {
        log.info("Atualizando servidor temporário com ID {}: {}", id, requestDTO);

        ServidorTemporario servidorTemporario = servidorTemporarioRepository.findById(id)
                .orElseThrow(() -> new ServidorTemporarioNotFoundException(id));

        Pessoa pessoa = pessoaRepository.findById(requestDTO.getPessoaId())
                .orElseThrow(() -> new PessoaNotFoundException(requestDTO.getPessoaId()));

        servidorTemporarioMapper.updateServidorTemporarioFromDTO(requestDTO, servidorTemporario, pessoa);
        servidorTemporario = servidorTemporarioRepository.save(servidorTemporario);

        return servidorTemporarioMapper.toServidorTemporarioResponseDTO(servidorTemporario);
    }

    public void deleteServidorTemporario(Long id) {
        log.info("Deletando servidor temporário com ID {}", id);
        if (!servidorTemporarioRepository.existsById(id)) {
            log.error("Servidor temporário com ID {} não encontrado.", id);
            throw new ServidorTemporarioNotFoundException(id);
        }
        servidorTemporarioRepository.deleteById(id);
    }
}