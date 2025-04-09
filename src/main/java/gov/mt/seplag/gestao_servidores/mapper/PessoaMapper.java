package gov.mt.seplag.gestao_servidores.mapper;

import gov.mt.seplag.gestao_servidores.dto.pessoa.PessoaRequestDTO;
import gov.mt.seplag.gestao_servidores.dto.pessoa.PessoaResponseDTO;
import gov.mt.seplag.gestao_servidores.entity.Pessoa;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PessoaMapper {

    PessoaResponseDTO toPessoaResponseDTO(Pessoa pessoa);

    @Mapping(target = "id", ignore = true)
    Pessoa toPessoaEntity(PessoaRequestDTO pessoaRequestDTO);

    @Mapping(target = "id", ignore = true)
    void updatePessoaFromDTO(PessoaRequestDTO pessoaRequestDTO, @MappingTarget Pessoa pessoa);
}