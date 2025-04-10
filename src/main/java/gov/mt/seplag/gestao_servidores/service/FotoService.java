package gov.mt.seplag.gestao_servidores.service;

import gov.mt.seplag.gestao_servidores.dto.foto_pessoa.FotoPessoaResponseDTO;
import gov.mt.seplag.gestao_servidores.entity.FotoPessoa;
import gov.mt.seplag.gestao_servidores.entity.Pessoa;
import gov.mt.seplag.gestao_servidores.exception.foto_pessoa.FotoPessoaNotFoundException;
import gov.mt.seplag.gestao_servidores.mapper.FotoPessoaMapper;
import gov.mt.seplag.gestao_servidores.repository.FotoPessoaRepository;
import gov.mt.seplag.gestao_servidores.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class FotoService {

    private final FotoPessoaRepository fotoPessoaRepository;
    private final FotoPessoaMapper fotoPessoaMapper;
    private final PessoaRepository pessoaRepository;
    private final PessoaService pessoaService;
    private final MinIOService minIOService;

    public Page<FotoPessoaResponseDTO> findAll(Pageable pageable) {
        log.info("Buscando todas as fotos.");
        Page<FotoPessoa> page = fotoPessoaRepository.findAll(pageable);
        return page.map(fotoPessoaMapper::toFotoPessoaResponseDTO);
    }

    public Page<FotoPessoaResponseDTO> listarFotosPorPessoa(Long pessoaId, Pageable pageable) {
        Pessoa pessoa = pessoaService.findPessoaById(pessoaId);
        Page<FotoPessoa> page = fotoPessoaRepository.findFotoPessoaByPessoa(pessoa, pageable);

        return page.map(foto -> {
            FotoPessoaResponseDTO dto = fotoPessoaMapper.toFotoPessoaResponseDTO(foto);
            dto.setUrlTemporaria(minIOService.gerarLinkTemporario(foto.getHash()));
            return dto;
        });
    }

    public FotoPessoaResponseDTO findById(Long id) {
        log.info("Buscando foto com ID {}", id);
        FotoPessoa fotoPessoa = fotoPessoaRepository.findById(id)
                .orElseThrow(() -> new FotoPessoaNotFoundException(id));
        return fotoPessoaMapper.toFotoPessoaResponseDTO(fotoPessoa);
    }

    public List<FotoPessoaResponseDTO> saveFotos(Long pessoaId, List<MultipartFile> fotos) {
        log.info("Salvando foto de usuário {}:", pessoaId);

        Pessoa pessoa = pessoaService.findPessoaById(pessoaId);
        List<FotoPessoa> savedFotos = new ArrayList<>();

        for (MultipartFile foto : fotos) {
            String hash = UUID.randomUUID().toString().replace("-", "");
            minIOService.uploadFoto(foto, hash);

            FotoPessoa fotoPessoa = new FotoPessoa();
            fotoPessoa.setPessoa(pessoa);
            fotoPessoa.setData(new Date());
            fotoPessoa.setBucket("fotos-pessoas");
            fotoPessoa.setHash(hash);

            savedFotos.add(fotoPessoaRepository.save(fotoPessoa));
        }

        return savedFotos.stream()
                .map(fotoPessoaMapper::toFotoPessoaResponseDTO)
                .toList();
    }

    public List<FotoPessoaResponseDTO> updateFotosPessoa(Long pessoaId, List<MultipartFile> novasFotos) {
        log.info("Atualizando fotos da pessoa com ID {}:", pessoaId);

        Pessoa pessoa = pessoaService.findPessoaById(pessoaId);

        // 1. Buscar e deletar fotos antigas
        List<FotoPessoa> fotosAntigas = fotoPessoaRepository.findFotoPessoaByPessoa(pessoa);
        for (FotoPessoa fotoAntiga : fotosAntigas) {
            try {
                minIOService.deleteFoto(fotoAntiga.getHash());
                fotoPessoaRepository.delete(fotoAntiga);
            } catch (Exception e) {
                log.warn("Erro ao excluir foto {} do MinIO: {}", fotoAntiga.getHash(), e.getMessage());
            }
        }

        // 2. Salvar novas fotos
        List<FotoPessoa> novasFotosSalvas = new ArrayList<>();
        for (MultipartFile novaFoto : novasFotos) {
            String novoHash = UUID.randomUUID().toString().replace("-", "");
            minIOService.uploadFoto(novaFoto, novoHash);

            FotoPessoa novaFotoPessoa = new FotoPessoa();
            novaFotoPessoa.setPessoa(pessoa);
            novaFotoPessoa.setData(new Date());
            novaFotoPessoa.setBucket("fotos-pessoas");
            novaFotoPessoa.setHash(novoHash);

            novasFotosSalvas.add(fotoPessoaRepository.save(novaFotoPessoa));
        }

        return novasFotosSalvas.stream()
                .map(fotoPessoaMapper::toFotoPessoaResponseDTO)
                .toList();
    }


    public void deleteFotoPessoa(Long id) {
        log.info("Deletando foto com ID {}", id);

        FotoPessoa fotoPessoa = fotoPessoaRepository.findById(id)
                .orElseThrow(() -> new FotoPessoaNotFoundException(id));

        try {
            minIOService.deleteFoto(fotoPessoa.getHash());
        } catch (Exception e) {
            log.warn("Erro ao excluir foto {} do MinIO: {}", fotoPessoa.getHash(), e.getMessage());
        }

        fotoPessoaRepository.deleteById(id);
    }
}