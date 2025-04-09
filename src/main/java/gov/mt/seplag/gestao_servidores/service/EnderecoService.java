package gov.mt.seplag.gestao_servidores.service;

import gov.mt.seplag.gestao_servidores.dto.endereco.EnderecoResponseDTO;
import gov.mt.seplag.gestao_servidores.dto.endereco.EnderecoRequestDTO;
import gov.mt.seplag.gestao_servidores.entity.Cidade;
import gov.mt.seplag.gestao_servidores.entity.Endereco;
import gov.mt.seplag.gestao_servidores.exception.endereco.EnderecoNotFoundException;
import gov.mt.seplag.gestao_servidores.exception.cidade.CidadeNotFoundException;
import gov.mt.seplag.gestao_servidores.mapper.EnderecoMapper;
import gov.mt.seplag.gestao_servidores.repository.CidadeRepository;
import gov.mt.seplag.gestao_servidores.repository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final CidadeRepository cidadeRepository;
    private final EnderecoMapper enderecoMapper;

    public Page<EnderecoResponseDTO> findAll(Pageable pageable) {
        log.info("Buscando todos os endereços.");
        Page<Endereco> page = enderecoRepository.findAll(pageable);
        Page<EnderecoResponseDTO> dtoPage = page.map(enderecoMapper::toEnderecoResponseDTO);

        log.debug("{} endereços localizados.", page.getTotalElements());
        return dtoPage;
    }

    public EnderecoResponseDTO findEnderecoById(Long id) {
        log.info("Buscando endereço com ID: {}", id);
        Optional<Endereco> enderecoOpt = enderecoRepository.findById(id);

        if (enderecoOpt.isPresent()) {
            EnderecoResponseDTO enderecoDTO = enderecoMapper.toEnderecoResponseDTO(enderecoOpt.get());
            log.debug("Endereço encontrado: {}", enderecoDTO);
            return enderecoDTO;
        } else {
            log.warn("Endereço com ID {} não encontrado.", id);
            throw new EnderecoNotFoundException(id);
        }
    }

    public EnderecoResponseDTO createEndereco(EnderecoRequestDTO enderecoRequestDTO) {
        log.info("Criando endereço: {}", enderecoRequestDTO);

        // Verifica se a cidade existe
        Cidade cidade = cidadeRepository.findById(enderecoRequestDTO.getCidadeId())
                .orElseThrow(() -> new CidadeNotFoundException(enderecoRequestDTO.getCidadeId()));

        // Converte o DTO para a entidade Endereco
        Endereco endereco = enderecoMapper.toEnderecoEntity(enderecoRequestDTO, cidade);
        Endereco savedEndereco = enderecoRepository.save(endereco);

        log.debug("Endereço criado com ID: {}", savedEndereco.getId());
        return enderecoMapper.toEnderecoResponseDTO(savedEndereco);
    }

    public EnderecoResponseDTO updateEndereco(Long id, EnderecoRequestDTO enderecoRequestDTO) {
        log.info("Atualizando endereço com ID: {} - {}", id, enderecoRequestDTO);

        // Busca o endereço existente
        Endereco existingEndereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new EnderecoNotFoundException(id));

        // Busca a cidade associada
        Cidade cidade = cidadeRepository.findById(enderecoRequestDTO.getCidadeId())
                .orElseThrow(() -> new CidadeNotFoundException(enderecoRequestDTO.getCidadeId()));

        // Atualiza os dados do endereço com os valores do DTO
        enderecoMapper.updateEnderecoFromDTO(enderecoRequestDTO, existingEndereco, cidade);

        Endereco updatedEndereco = enderecoRepository.save(existingEndereco);
        log.debug("Endereço atualizado: {}", updatedEndereco);

        return enderecoMapper.toEnderecoResponseDTO(updatedEndereco);
    }

    public void deleteEndereco(Long id) {
        log.info("Apagando endereço com ID: {}", id);

        if (enderecoRepository.existsById(id)) {
            enderecoRepository.deleteById(id);
            log.debug("Endereço com ID {} deletado com sucesso.", id);
        } else {
            log.error("Endereço com ID {} não localizado.", id);
            throw new EnderecoNotFoundException(id);
        }
    }
}