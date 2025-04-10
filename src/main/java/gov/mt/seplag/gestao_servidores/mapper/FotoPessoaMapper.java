package gov.mt.seplag.gestao_servidores.mapper;

import gov.mt.seplag.gestao_servidores.dto.foto_pessoa.FotoPessoaRequestDTO;
import gov.mt.seplag.gestao_servidores.dto.foto_pessoa.FotoPessoaResponseDTO;
import gov.mt.seplag.gestao_servidores.entity.FotoPessoa;
import gov.mt.seplag.gestao_servidores.entity.Pessoa;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = PessoaMapper.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FotoPessoaMapper {

    @Mapping(source = "hash", target = "urlTemporaria")
    FotoPessoaResponseDTO toFotoPessoaResponseDTO(FotoPessoa fotoPessoa);

    FotoPessoa toFotoPessoaEntity(FotoPessoaRequestDTO fotoPessoaRequestDTO, Pessoa pessoa);

    void updateFotoPessoaFromDTO(FotoPessoaRequestDTO fotoPessoaRequestDTO, @MappingTarget FotoPessoa fotoPessoa, Pessoa pessoa);
}