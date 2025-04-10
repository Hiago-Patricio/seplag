package gov.mt.seplag.gestao_servidores.controller;

import gov.mt.seplag.gestao_servidores.dto.foto_pessoa.FotoPessoaResponseDTO;
import gov.mt.seplag.gestao_servidores.service.FotoService;
import gov.mt.seplag.gestao_servidores.service.MinIOService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/foto")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class FotoController {

    private final FotoService fotoService;

    @PostMapping(value = "/upload/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<FotoPessoaResponseDTO>> uploadFoto(
            @PathVariable("id") Long id,
            @RequestParam("fotos") List<MultipartFile> fotos) {

        List<FotoPessoaResponseDTO> savedFotos = fotoService.saveFotos(id, fotos);

        return ResponseEntity.ok(savedFotos);
    }

    @PutMapping(value = "/upload/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<FotoPessoaResponseDTO>> updatedFoto(
            @PathVariable("id") Long id,
            @RequestParam("fotos") List<MultipartFile> fotos) {

        List<FotoPessoaResponseDTO> savedFotos = fotoService.updateFotosPessoa(id, fotos);

        return ResponseEntity.ok(savedFotos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<FotoPessoaResponseDTO>> listarFotosPorPessoa(
            @PathVariable("id") Long id,
            @ParameterObject @Parameter(description = "Parâmetros de paginação") Pageable pageable) {
        Page<FotoPessoaResponseDTO> fotos = fotoService.listarFotosPorPessoa(id, pageable);
        return ResponseEntity.ok(fotos.getContent());
    }
}