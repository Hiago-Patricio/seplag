package gov.mt.seplag.gestao_servidores.mapper;

import gov.mt.seplag.gestao_servidores.dto.unidade.UnidadeRequestDTO;
import gov.mt.seplag.gestao_servidores.dto.unidade.UnidadeResponseDTO;
import gov.mt.seplag.gestao_servidores.entity.Unidade;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UnidadeMapper {

    UnidadeResponseDTO toUnidadeResponseDTO(Unidade unidade);

    @Mapping(target = "id", ignore = true)
    Unidade toUnidadeEntity(UnidadeRequestDTO unidadeRequestDTO);

    @Mapping(target = "id", ignore = true)
    void updateUnidadeFromDTO(UnidadeRequestDTO unidadeRequestDTO, @MappingTarget Unidade unidade);
}