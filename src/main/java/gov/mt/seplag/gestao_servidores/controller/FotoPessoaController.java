package gov.mt.seplag.gestao_servidores.controller;

import gov.mt.seplag.gestao_servidores.dto.foto_pessoa.FotoPessoaRequestDTO;
import gov.mt.seplag.gestao_servidores.dto.foto_pessoa.FotoPessoaResponseDTO;
import gov.mt.seplag.gestao_servidores.service.FotoPessoaService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("api/fotos-pessoa")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class FotoPessoaController {

    private final FotoPessoaService fotoPessoaService;

    @GetMapping
    public ResponseEntity<List<FotoPessoaResponseDTO>> getAllFotosPessoa(
            @ParameterObject @Parameter(description = "Parâmetros de paginação") Pageable pageable) {
        Page<FotoPessoaResponseDTO> fotosPessoa = fotoPessoaService.findAll(pageable);
        return ResponseEntity.ok(fotosPessoa.getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FotoPessoaResponseDTO> getFotoPessoaById(@PathVariable Long id) {
        FotoPessoaResponseDTO fotoPessoa = fotoPessoaService.findById(id);
        return ResponseEntity.ok(fotoPessoa);
    }

    @PostMapping
    public ResponseEntity<FotoPessoaResponseDTO> createFotoPessoa(@Valid @RequestBody FotoPessoaRequestDTO requestDTO) {
        return ResponseEntity.ok(fotoPessoaService.createFotoPessoa(requestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FotoPessoaResponseDTO> updateFotoPessoa(@PathVariable Long id, @Valid @RequestBody FotoPessoaRequestDTO requestDTO) {
        return ResponseEntity.ok(fotoPessoaService.updateFotoPessoa(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFotoPessoa(@PathVariable Long id) {
        fotoPessoaService.deleteFotoPessoa(id);
        return ResponseEntity.noContent().build();
    }
}