package gov.mt.seplag.gestao_servidores.controller;

import gov.mt.seplag.gestao_servidores.dto.servidor_efetivo.ServidorEfeitivoByUnidadeResponseDTO;
import gov.mt.seplag.gestao_servidores.dto.servidor_efetivo.ServidorEfetivoRequestDTO;
import gov.mt.seplag.gestao_servidores.dto.servidor_efetivo.ServidorEfetivoResponseDTO;
import gov.mt.seplag.gestao_servidores.service.ServidorEfetivoService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/servidores-efetivos")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class ServidorEfetivoController {

    private final ServidorEfetivoService servidorEfetivoService;

    @GetMapping
    public ResponseEntity<List<ServidorEfetivoResponseDTO>> getAllServidoresEfetivos(
            @ParameterObject @Parameter(description = "Parâmetros de paginação") Pageable pageable) {
        Page<ServidorEfetivoResponseDTO> servidoresEfetivos = servidorEfetivoService.findAll(pageable);
        return ResponseEntity.ok(servidoresEfetivos.getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServidorEfetivoResponseDTO> getServidorEfetivoById(@PathVariable Long id) {
        ServidorEfetivoResponseDTO servidorEfetivo = servidorEfetivoService.findById(id);
        return ResponseEntity.ok(servidorEfetivo);
    }

    @GetMapping("/unidade/{id}")
    public ResponseEntity<List<ServidorEfeitivoByUnidadeResponseDTO>> getServidorEfetivoByUnidadeId(
        @PathVariable Long id,
        @ParameterObject @Parameter(description = "Parâmetros de paginação") Pageable pageable)
    {
        List<ServidorEfeitivoByUnidadeResponseDTO> servidoresEfetivos = servidorEfetivoService.findByUnidadeId(id, pageable);
        return ResponseEntity.ok(servidoresEfetivos);
    }

    @PostMapping
    public ResponseEntity<ServidorEfetivoResponseDTO> createServidorEfetivo(@Valid @RequestBody ServidorEfetivoRequestDTO requestDTO) {
        return ResponseEntity.ok(servidorEfetivoService.createServidorEfetivo(requestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServidorEfetivoResponseDTO> updateServidorEfetivo(@PathVariable Long id, @Valid @RequestBody ServidorEfetivoRequestDTO requestDTO) {
        return ResponseEntity.ok(servidorEfetivoService.updateServidorEfetivo(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServidorEfetivo(@PathVariable Long id) {
        servidorEfetivoService.deleteServidorEfetivo(id);
        return ResponseEntity.noContent().build();
    }
}