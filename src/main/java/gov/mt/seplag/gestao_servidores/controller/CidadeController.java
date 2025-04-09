package gov.mt.seplag.gestao_servidores.controller;

import gov.mt.seplag.gestao_servidores.dto.cidade.CidadeResponseDTO;
import gov.mt.seplag.gestao_servidores.dto.cidade.CidadeRequestDTO;
import gov.mt.seplag.gestao_servidores.service.CidadeService;
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
@RequestMapping("api/cidades")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class CidadeController {
    private final CidadeService cidadeService;

    @GetMapping
    public ResponseEntity<List<CidadeResponseDTO>> getAllCidades(
            @ParameterObject
            @Parameter(
                    description = "Parâmetros de paginação",
                    examples = {
                            @io.swagger.v3.oas.annotations.media.ExampleObject(name = "Exemplo de paginação", value= "page=0&size=10&sort=name,asc")
                    }
            )
            Pageable pageable) {
        Page<CidadeResponseDTO> cidades = cidadeService.findAll(pageable);
        return ResponseEntity.ok(cidades.getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CidadeResponseDTO> getCidadeById(@PathVariable Long id) {
        CidadeResponseDTO cidade = cidadeService.findCidadeById(id);

        return ResponseEntity.ok(cidade);
    }

    @PostMapping
    public CidadeResponseDTO createCidade(@Valid @RequestBody CidadeRequestDTO cidade) {
        return cidadeService.createCidade(cidade);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CidadeResponseDTO> updateCidade(@PathVariable Long id, @Valid @RequestBody CidadeRequestDTO cidade) {
        CidadeResponseDTO updatedCidade = cidadeService.updateCidade(id, cidade);
        return ResponseEntity.ok(updatedCidade);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCidade(@PathVariable Long id) {
        cidadeService.deleteCidade(id);
        return ResponseEntity.noContent().build();
    }
}
