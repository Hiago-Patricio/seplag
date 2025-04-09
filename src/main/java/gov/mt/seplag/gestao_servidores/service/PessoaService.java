package gov.mt.seplag.gestao_servidores.service;

import gov.mt.seplag.gestao_servidores.dto.endereco.EnderecoRequestDTO;
import gov.mt.seplag.gestao_servidores.dto.pessoa.PessoaResponseDTO;
import gov.mt.seplag.gestao_servidores.dto.pessoa.PessoaRequestDTO;
import gov.mt.seplag.gestao_servidores.entity.Endereco;
import gov.mt.seplag.gestao_servidores.entity.Pessoa;
import gov.mt.seplag.gestao_servidores.exception.pessoa.PessoaNotFoundException;
import gov.mt.seplag.gestao_servidores.mapper.EnderecoMapper;
import gov.mt.seplag.gestao_servidores.mapper.PessoaMapper;
import gov.mt.seplag.gestao_servidores.repository.PessoaRepository;
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
public class PessoaService {

    private final PessoaRepository pessoaRepository;
    private final PessoaMapper pessoaMapper;
    private final EnderecoService enderecoService;
    private final EnderecoMapper enderecoMapper;

    public Page<PessoaResponseDTO> findAll(Pageable pageable) {
        log.info("Buscando todas as pessoas.");
        
        Page<Pessoa> page = pessoaRepository.findAll(pageable);
        Page<PessoaResponseDTO> dtoPage = page.map(pessoaMapper::toPessoaResponseDTO);

        log.debug("{} pessoas localizadas.", page.getTotalElements());
        return dtoPage;
    }

    public Pessoa findPessoaById(Long id) {
        log.info("Buscando pessoa com ID: {}", id);
        Optional<Pessoa> pessoaOpt = pessoaRepository.findById(id);

        if (pessoaOpt.isPresent()) {
            log.debug("Pessoa encontrada: {}", pessoaOpt.get());
            return pessoaOpt.get();
        } else {
            log.warn("Pessoa com ID {} não encontrada.", id);
            throw new PessoaNotFoundException(id);
        }
    }

    public Pessoa createPessoa(PessoaRequestDTO pessoaRequestDTO) {
        log.info("Criando pessoa: {}", pessoaRequestDTO);
        Pessoa pessoaEntity = pessoaMapper.toPessoaEntity(pessoaRequestDTO);

        List<Endereco> enderecos = new ArrayList<>();
        for (EnderecoRequestDTO enderecoDTO : pessoaRequestDTO.getEnderecos()) {
            Endereco endereco = enderecoService.createEndereco(enderecoDTO);
            enderecos.add(endereco);
        }
        pessoaEntity.setEnderecos(enderecos);

        Pessoa savedPessoa = pessoaRepository.save(pessoaEntity);
        log.debug("Pessoa criada com ID: {}", savedPessoa.getId());
        return savedPessoa;
    }

    public Pessoa updatePessoa(Long id, PessoaRequestDTO pessoaRequestDTO) {
        log.info("Atualizando pessoa com ID: {} - {}", id, pessoaRequestDTO);

        Pessoa existingPessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new PessoaNotFoundException(id));

        List<Endereco> enderecos = new ArrayList<>();
        for (Endereco endereco : existingPessoa.getEnderecos()) {
            Endereco updatedEndereco = enderecoService.updateEndereco(endereco.getId(), endereco);
            enderecos.add(updatedEndereco);
        }
        existingPessoa.setEnderecos(enderecos);

        pessoaMapper.updatePessoaFromDTO(pessoaRequestDTO, existingPessoa);

        Pessoa updatedPessoa = pessoaRepository.save(existingPessoa);
        log.debug("Pessoa atualizada com sucesso: {}", updatedPessoa);

        return updatedPessoa;
    }

    public void deletePessoa(Long id) {
        log.info("Apagando pessoa com ID: {}", id);
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);

        if (pessoa.isPresent()) {
            List<Endereco> enderecos = pessoa.get().getEnderecos();

            pessoaRepository.deleteById(id);
            log.debug("Pessoa com ID {} deletada com sucesso.", id);

            for (Endereco endereco : enderecos) {
                enderecoService.deleteEndereco(endereco.getId());
            }
        } else {
            log.error("Pessoa com ID {} não localizada.", id);
            throw new PessoaNotFoundException(id);
        }
    }
}