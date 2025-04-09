package gov.mt.seplag.gestao_servidores.controller;

import gov.mt.seplag.gestao_servidores.dto.endereco.EnderecoResponseDTO;
import gov.mt.seplag.gestao_servidores.dto.endereco.EnderecoRequestDTO;
import gov.mt.seplag.gestao_servidores.service.EnderecoService;
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
@RequestMapping("api/enderecos")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class EnderecoController {

    private final EnderecoService enderecoService;

    @GetMapping
    public ResponseEntity<List<EnderecoResponseDTO>> getAllEnderecos(
            @ParameterObject
            @Parameter(
                    description = "Parâmetros de paginação",
                    examples = {
                            @io.swagger.v3.oas.annotations.media.ExampleObject(name = "Exemplo de paginação", value = "page=0&size=10&sort=logradouro,asc")
                    }
            )
            Pageable pageable) {
        Page<EnderecoResponseDTO> enderecos = enderecoService.findAll(pageable);
        return ResponseEntity.ok(enderecos.getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoResponseDTO> getEnderecoById(@PathVariable Long id) {
        EnderecoResponseDTO endereco = enderecoService.findEnderecoById(id);
        return ResponseEntity.ok(endereco);
    }

    @PostMapping
    public ResponseEntity<EnderecoResponseDTO> createEndereco(@Valid @RequestBody EnderecoRequestDTO enderecoRequestDTO) {
        EnderecoResponseDTO createdEndereco = enderecoService.createEndereco(enderecoRequestDTO);
        return ResponseEntity.ok(createdEndereco);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoResponseDTO> updateEndereco(@PathVariable Long id, @Valid @RequestBody EnderecoRequestDTO enderecoRequestDTO) {
        EnderecoResponseDTO updatedEndereco = enderecoService.updateEndereco(id, enderecoRequestDTO);
        return ResponseEntity.ok(updatedEndereco);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEndereco(@PathVariable Long id) {
        enderecoService.deleteEndereco(id);
        return ResponseEntity.noContent().build();
    }
}