package gov.mt.seplag.gestao_servidores.service;

import gov.mt.seplag.gestao_servidores.dto.endereco.EnderecoRequestDTO;
import gov.mt.seplag.gestao_servidores.dto.endereco.EnderecoResponseDTO;
import gov.mt.seplag.gestao_servidores.dto.unidade.UnidadeRequestDTO;
import gov.mt.seplag.gestao_servidores.dto.unidade.UnidadeResponseDTO;
import gov.mt.seplag.gestao_servidores.entity.Cidade;
import gov.mt.seplag.gestao_servidores.entity.Endereco;
import gov.mt.seplag.gestao_servidores.entity.Unidade;
import gov.mt.seplag.gestao_servidores.exception.unidade.UnidadeNotFoundException;
import gov.mt.seplag.gestao_servidores.mapper.CidadeMapper;
import gov.mt.seplag.gestao_servidores.mapper.EnderecoMapper;
import gov.mt.seplag.gestao_servidores.mapper.UnidadeMapper;
import gov.mt.seplag.gestao_servidores.repository.UnidadeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UnidadeService {

    private final UnidadeRepository unidadeRepository;
    private final UnidadeMapper unidadeMapper;
    private final EnderecoService enderecoService;

    public Page<UnidadeResponseDTO> findAll(Pageable pageable) {
        log.info("Buscando todas as unidades.");

        Page<Unidade> page = unidadeRepository.findAll(pageable);
        Page<UnidadeResponseDTO> dtoPage = page.map(unidadeMapper::toUnidadeResponseDTO);

        log.debug("{} unidades localizadas.", page.getTotalElements());
        return dtoPage;
    }

    public UnidadeResponseDTO findUnidadeById(Long id) {
        log.info("Buscando unidade com ID: {}", id);
        Optional<Unidade> unidadeOptional = unidadeRepository.findById(id);

        if (unidadeOptional.isPresent()) {
            UnidadeResponseDTO unidadeResponseDTO = unidadeMapper.toUnidadeResponseDTO(unidadeOptional.get());
            log.debug("Unidade encontrada: {}", unidadeResponseDTO);
            return unidadeResponseDTO;
        } else {
            log.warn("Unidade com ID {} não encontrada.", id);
            throw new UnidadeNotFoundException(id);
        }
    }

    public UnidadeResponseDTO createUnidade(UnidadeRequestDTO unidadeRequestDTO) {
        log.info("Criando unidade: {}", unidadeRequestDTO);
        Unidade unidadeEntity = unidadeMapper.toUnidadeEntity(unidadeRequestDTO);

        List<Endereco> enderecos = new ArrayList<>();
        for (EnderecoRequestDTO enderecoDTO : unidadeRequestDTO.getEnderecos()) {
            Endereco endereco = enderecoService.createEndereco(enderecoDTO);
            enderecos.add(endereco);
        }
        unidadeEntity.setEnderecos(enderecos);

        Unidade savedUnidade = unidadeRepository.save(unidadeEntity);
        log.debug("Unidade criada com ID: {}", savedUnidade.getId());
        return unidadeMapper.toUnidadeResponseDTO(savedUnidade);
    }

    public UnidadeResponseDTO updateUnidade(Long id, UnidadeRequestDTO unidadeRequestDTO) {
        log.info("Atualizando unidade com ID: {} - {}", id, unidadeRequestDTO);

        Unidade existingUnidade = unidadeRepository.findById(id)
                .orElseThrow(() -> new UnidadeNotFoundException(id));

        List<Endereco> enderecos = new ArrayList<>();
        for (Endereco endereco : existingUnidade.getEnderecos()) {
            Endereco existingEndereco = enderecoService.updateEndereco(endereco.getId(), endereco);
            enderecos.add(existingEndereco);
        }
        existingUnidade.setEnderecos(enderecos);

        unidadeMapper.updateUnidadeFromDTO(unidadeRequestDTO, existingUnidade);

        Unidade updatedUnidade = unidadeRepository.save(existingUnidade);
        log.debug("Unidade atualizada com sucesso: {}", updatedUnidade);

        return unidadeMapper.toUnidadeResponseDTO(updatedUnidade);
    }

    public void deleteUnidade(Long id) {
        log.info("Apagando unidade com ID: {}", id);
        Optional<Unidade> unidade = unidadeRepository.findById(id);

        if (unidade.isPresent()) {
            List<Endereco> enderecos = unidade.get().getEnderecos();

            unidadeRepository.deleteById(id);
            log.debug("Unidade com ID {} deletada com sucesso.", id);

            for (Endereco endereco : enderecos) {
                enderecoService.deleteEndereco(endereco.getId());
            }
        } else {
            log.error("Unidade com ID {} não localizada.", id);
            throw new UnidadeNotFoundException(id);
        }
    }
}