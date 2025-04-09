package gov.mt.seplag.gestao_servidores.controller;

import gov.mt.seplag.gestao_servidores.dto.unidade.UnidadeResponseDTO;
import gov.mt.seplag.gestao_servidores.dto.unidade.UnidadeRequestDTO;
import gov.mt.seplag.gestao_servidores.service.UnidadeService;
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
@RequestMapping("api/unidades")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class UnidadeController {

    private final UnidadeService unidadeService;

    @GetMapping
    public ResponseEntity<List<UnidadeResponseDTO>> getAllUnidades(
            @ParameterObject
            @Parameter(
                    description = "Parâmetros de paginação",
                    examples = {
                            @io.swagger.v3.oas.annotations.media.ExampleObject(name = "Exemplo de paginação", value = "page=0&size=10&sort=nome,asc")
                    }
            )
            Pageable pageable) {
        Page<UnidadeResponseDTO> unidades = unidadeService.findAll(pageable);
        return ResponseEntity.ok(unidades.getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnidadeResponseDTO> getUnidadeById(@PathVariable Long id) {
        UnidadeResponseDTO unidade = unidadeService.findUnidadeById(id);
        return ResponseEntity.ok(unidade);
    }

    @PostMapping
    public UnidadeResponseDTO createUnidade(@Valid @RequestBody UnidadeRequestDTO unidadeRequestDTO) {
        return unidadeService.createUnidade(unidadeRequestDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UnidadeResponseDTO> updateUnidade(@PathVariable Long id, @Valid @RequestBody UnidadeRequestDTO unidadeRequestDTO) {
        UnidadeResponseDTO updatedUnidade = unidadeService.updateUnidade(id, unidadeRequestDTO);
        return ResponseEntity.ok(updatedUnidade);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUnidade(@PathVariable Long id) {
        unidadeService.deleteUnidade(id);
        return ResponseEntity.noContent().build();
    }
}