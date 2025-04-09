package gov.mt.seplag.gestao_servidores.mapper;

import gov.mt.seplag.gestao_servidores.dto.foto_pessoa.FotoPessoaRequestDTO;
import gov.mt.seplag.gestao_servidores.dto.foto_pessoa.FotoPessoaResponseDTO;
import gov.mt.seplag.gestao_servidores.entity.FotoPessoa;
import gov.mt.seplag.gestao_servidores.entity.Pessoa;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = PessoaMapper.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FotoPessoaMapper {

    @Mapping(target = "pessoa", source = "pessoa") // Agora mapeia diretamente a entidade Pessoa
    FotoPessoaResponseDTO toFotoPessoaResponseDTO(FotoPessoa fotoPessoa);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "pessoa", source = "pessoa") // Usa diretamente a entidade Pessoa do RequestDTO
    FotoPessoa toFotoPessoaEntity(FotoPessoaRequestDTO fotoPessoaRequestDTO, Pessoa pessoa);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "pessoa", source = "pessoa")
    void updateFotoPessoaFromDTO(FotoPessoaRequestDTO fotoPessoaRequestDTO, @MappingTarget FotoPessoa fotoPessoa, Pessoa pessoa);
}