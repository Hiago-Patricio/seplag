package gov.mt.seplag.gestao_servidores.controller;

import gov.mt.seplag.gestao_servidores.dto.lotacao.LotacaoRequestDTO;
import gov.mt.seplag.gestao_servidores.dto.lotacao.LotacaoResponseDTO;
import gov.mt.seplag.gestao_servidores.service.LotacaoService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/lotacoes")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class LotacaoController {

    private final LotacaoService lotacaoService;

    @GetMapping
    public ResponseEntity<Page<LotacaoResponseDTO>> getAllLotacoes(
            @ParameterObject @Parameter(description = "Parâmetros de paginação") Pageable pageable) {
        Page<LotacaoResponseDTO> lotacoes = lotacaoService.findAll(pageable);
        return ResponseEntity.ok(lotacoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LotacaoResponseDTO> getLotacaoById(@PathVariable Long id) {
        LotacaoResponseDTO lotacao = lotacaoService.findById(id);
        return ResponseEntity.ok(lotacao);
    }

    @PostMapping
    public ResponseEntity<LotacaoResponseDTO> createLotacao(@Valid @RequestBody LotacaoRequestDTO requestDTO) {
        return ResponseEntity.ok(lotacaoService.createLotacao(requestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LotacaoResponseDTO> updateLotacao(@PathVariable Long id, @Valid @RequestBody LotacaoRequestDTO requestDTO) {
        return ResponseEntity.ok(lotacaoService.updateLotacao(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLotacao(@PathVariable Long id) {
        lotacaoService.deleteLotacao(id);
        return ResponseEntity.noContent().build();
    }
}