package gov.mt.seplag.gestao_servidores.controller;

import gov.mt.seplag.gestao_servidores.dto.pessoa.PessoaResponseDTO;
import gov.mt.seplag.gestao_servidores.dto.pessoa.PessoaRequestDTO;
import gov.mt.seplag.gestao_servidores.service.PessoaService;
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
@RequestMapping("api/pessoas")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class PessoaController {

    private final PessoaService pessoaService;

    @GetMapping
    public ResponseEntity<List<PessoaResponseDTO>> getAllPessoas(
            @ParameterObject
            @Parameter(
                    description = "Parâmetros de paginação",
                    examples = {
                            @io.swagger.v3.oas.annotations.media.ExampleObject(name = "Exemplo de paginação", value = "page=0&size=10&sort=nome,asc")
                    }
            )
            Pageable pageable) {
        Page<PessoaResponseDTO> pessoas = pessoaService.findAll(pageable);
        return ResponseEntity.ok(pessoas.getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaResponseDTO> getPessoaById(@PathVariable Long id) {
        PessoaResponseDTO pessoa = pessoaService.findPessoaById(id);
        return ResponseEntity.ok(pessoa);
    }

    @PostMapping
    public PessoaResponseDTO createPessoa(@Valid @RequestBody PessoaRequestDTO pessoaRequestDTO) {
        return pessoaService.createPessoa(pessoaRequestDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaResponseDTO> updatePessoa(@PathVariable Long id, @Valid @RequestBody PessoaRequestDTO pessoaRequestDTO) {
        PessoaResponseDTO updatedPessoa = pessoaService.updatePessoa(id, pessoaRequestDTO);
        return ResponseEntity.ok(updatedPessoa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePessoa(@PathVariable Long id) {
        pessoaService.deletePessoa(id);
        return ResponseEntity.noContent().build();
    }
}