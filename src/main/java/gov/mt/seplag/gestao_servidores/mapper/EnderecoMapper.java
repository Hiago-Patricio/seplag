package gov.mt.seplag.gestao_servidores.mapper;

import gov.mt.seplag.gestao_servidores.dto.endereco.EnderecoResponseDTO;
import gov.mt.seplag.gestao_servidores.dto.endereco.EnderecoRequestDTO;
import gov.mt.seplag.gestao_servidores.entity.Cidade;
import gov.mt.seplag.gestao_servidores.entity.Endereco;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EnderecoMapper {

    @Mapping(target = "cidade", source = "cidade")
    EnderecoResponseDTO toEnderecoResponseDTO(Endereco endereco);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cidade", source = "cidade")
    Endereco toEnderecoEntity(EnderecoRequestDTO enderecoDTO, Cidade cidade);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cidade", source = "cidade")
    Endereco toEnderecoEntity(EnderecoResponseDTO enderecoDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cidade", source = "cidade")
    void updateEnderecoFromDTO(EnderecoRequestDTO enderecoDTO, @MappingTarget Endereco endereco, Cidade cidade);
}