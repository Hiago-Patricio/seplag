package gov.mt.seplag.gestao_servidores.service;

import gov.mt.seplag.gestao_servidores.dto.foto_pessoa.FotoPessoaRequestDTO;
import gov.mt.seplag.gestao_servidores.dto.foto_pessoa.FotoPessoaResponseDTO;
import gov.mt.seplag.gestao_servidores.entity.FotoPessoa;
import gov.mt.seplag.gestao_servidores.entity.Pessoa;
import gov.mt.seplag.gestao_servidores.exception.foto_pessoa.FotoPessoaNotFoundException;
import gov.mt.seplag.gestao_servidores.exception.pessoa.PessoaNotFoundException;
import gov.mt.seplag.gestao_servidores.mapper.FotoPessoaMapper;
import gov.mt.seplag.gestao_servidores.repository.FotoPessoaRepository;
import gov.mt.seplag.gestao_servidores.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FotoPessoaService {

    private final FotoPessoaRepository fotoPessoaRepository;
    private final FotoPessoaMapper fotoPessoaMapper;
    private final PessoaRepository pessoaRepository;

    public Page<FotoPessoaResponseDTO> findAll(Pageable pageable) {
        log.info("Buscando todas as fotos.");
        Page<FotoPessoa> page = fotoPessoaRepository.findAll(pageable);
        return page.map(fotoPessoaMapper::toFotoPessoaResponseDTO);
    }

    public FotoPessoaResponseDTO findById(Long id) {
        log.info("Buscando foto com ID {}", id);
        FotoPessoa fotoPessoa = fotoPessoaRepository.findById(id)
                .orElseThrow(() -> new FotoPessoaNotFoundException(id));
        return fotoPessoaMapper.toFotoPessoaResponseDTO(fotoPessoa);
    }

    public FotoPessoaResponseDTO createFotoPessoa(FotoPessoaRequestDTO requestDTO) {
        log.info("Criando foto: {}", requestDTO);

        Pessoa pessoa = pessoaRepository.findById(requestDTO.getPessoaId())
                .orElseThrow(() -> new PessoaNotFoundException(requestDTO.getPessoaId()));

        FotoPessoa fotoPessoa = fotoPessoaMapper.toFotoPessoaEntity(requestDTO, pessoa);
        FotoPessoa savedFotoPessoa = fotoPessoaRepository.save(fotoPessoa);

        return fotoPessoaMapper.toFotoPessoaResponseDTO(savedFotoPessoa);
    }

    public FotoPessoaResponseDTO updateFotoPessoa(Long id, FotoPessoaRequestDTO requestDTO) {
        log.info("Atualizando foto com ID {}: {}", id, requestDTO);

        FotoPessoa fotoPessoa = fotoPessoaRepository.findById(id)
                .orElseThrow(() -> new FotoPessoaNotFoundException(id));

        Pessoa pessoa = pessoaRepository.findById(requestDTO.getPessoaId())
                        .orElseThrow(() -> new PessoaNotFoundException(requestDTO.getPessoaId()));

        fotoPessoaMapper.updateFotoPessoaFromDTO(requestDTO, fotoPessoa, pessoa);
        FotoPessoa updatedFotoPessoa = fotoPessoaRepository.save(fotoPessoa);

        return fotoPessoaMapper.toFotoPessoaResponseDTO(updatedFotoPessoa);
    }

    public void deleteFotoPessoa(Long id) {
        log.info("Deletando foto com ID {}", id);
        if (!fotoPessoaRepository.existsById(id)) {
            log.error("Foto com ID {} não encontrada.", id);
            throw new FotoPessoaNotFoundException(id);
        }
        fotoPessoaRepository.deleteById(id);
    }
}