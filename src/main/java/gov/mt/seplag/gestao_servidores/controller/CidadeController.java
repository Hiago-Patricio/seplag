package gov.mt.seplag.gestao_servidores.controller;

import gov.mt.seplag.gestao_servidores.dto.cidade.CidadeDTO;
import gov.mt.seplag.gestao_servidores.dto.cidade.CreateCidadeDTO;
import gov.mt.seplag.gestao_servidores.dto.cidade.UpdateCidadeDTO;
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
    public ResponseEntity<List<CidadeDTO>> getAllCidades(
            @ParameterObject
            @Parameter(
                    description = "Parameters of pagination",
                    examples = {
                            @io.swagger.v3.oas.annotations.media.ExampleObject(name = "Pagination example", value= "page=0&size=10&sort=name,asc")
                    }
            )
            Pageable pageable) {
        Page<CidadeDTO> cidades = cidadeService.findAll(pageable);
        return ResponseEntity.ok(cidades.getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CidadeDTO> getCidadeById(@PathVariable Long id) {
        CidadeDTO cidade = cidadeService.findCidadeById(id);

        return ResponseEntity.ok(cidade);
    }

    @PostMapping
    public CidadeDTO createCidade(@Valid @RequestBody CreateCidadeDTO cidade) {
        return cidadeService.createCidade(cidade);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CidadeDTO> updateCidade(@PathVariable Long id, @Valid @RequestBody UpdateCidadeDTO cidade) {
        CidadeDTO updatedCidade = cidadeService.updateCidade(id, cidade);
        return ResponseEntity.ok(updatedCidade);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCidade(@PathVariable Long id) {
        cidadeService.deleteCidade(id);
        return ResponseEntity.noContent().build();
    }
}
