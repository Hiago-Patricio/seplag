package gov.mt.seplag.gestao_servidores.mapper;

import gov.mt.seplag.gestao_servidores.dto.cidade.CidadeResponseDTO;
import gov.mt.seplag.gestao_servidores.dto.cidade.CidadeRequestDTO;
import gov.mt.seplag.gestao_servidores.entity.Cidade;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CidadeMapper {
    CidadeResponseDTO toCidadeDTO(Cidade cidade);

    Cidade toEntity(CidadeRequestDTO cidadeDTO);
    Cidade toEntity(CidadeResponseDTO cidadeDTO);

    void updateEntityFromDTO(CidadeRequestDTO updateCidadeDTO, @MappingTarget Cidade cidade);
}
