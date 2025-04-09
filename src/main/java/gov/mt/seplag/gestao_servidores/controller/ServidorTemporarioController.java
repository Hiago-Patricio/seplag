package gov.mt.seplag.gestao_servidores.controller;

import gov.mt.seplag.gestao_servidores.dto.servidor_temporario.ServidorTemporarioRequestDTO;
import gov.mt.seplag.gestao_servidores.dto.servidor_temporario.ServidorTemporarioResponseDTO;
import gov.mt.seplag.gestao_servidores.service.ServidorTemporarioService;
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
@RequestMapping("api/servidores-temporarios")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class ServidorTemporarioController {

    private final ServidorTemporarioService servidorTemporarioService;

    @GetMapping
    public ResponseEntity<List<ServidorTemporarioResponseDTO>> getAllServidoresTemporarios(
            @ParameterObject @Parameter(description = "Parâmetros de paginação") Pageable pageable) {
        Page<ServidorTemporarioResponseDTO> servidoresTemporarios = servidorTemporarioService.findAll(pageable);
        return ResponseEntity.ok(servidoresTemporarios.getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServidorTemporarioResponseDTO> getServidorTemporarioById(@PathVariable Long id) {
        ServidorTemporarioResponseDTO servidorTemporario = servidorTemporarioService.findById(id);
        return ResponseEntity.ok(servidorTemporario);
    }

    @PostMapping
    public ResponseEntity<ServidorTemporarioResponseDTO> createServidorTemporario(@Valid @RequestBody ServidorTemporarioRequestDTO requestDTO) {
        return ResponseEntity.ok(servidorTemporarioService.createServidorTemporario(requestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServidorTemporarioResponseDTO> updateServidorTemporario(@PathVariable Long id,
            @Valid @RequestBody ServidorTemporarioRequestDTO requestDTO) {
        return ResponseEntity.ok(servidorTemporarioService.updateServidorTemporario(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServidorTemporario(@PathVariable Long id) {
        servidorTemporarioService.deleteServidorTemporario(id);
        return ResponseEntity.noContent().build();
    }
}