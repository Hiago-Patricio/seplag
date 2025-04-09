package gov.mt.seplag.gestao_servidores.mapper;

import gov.mt.seplag.gestao_servidores.dto.lotacao.LotacaoRequestDTO;
import gov.mt.seplag.gestao_servidores.dto.lotacao.LotacaoResponseDTO;
import gov.mt.seplag.gestao_servidores.entity.Lotacao;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LotacaoMapper {

    LotacaoResponseDTO toLotacaoResponseDTO(Lotacao lotacao);

    @Mapping(target = "id", ignore = true)
    Lotacao toLotacaoEntity(LotacaoRequestDTO lotacaoRequestDTO);

    @Mapping(target = "id", ignore = true)
    void updateLotacaoFromDTO(LotacaoRequestDTO lotacaoRequestDTO, @MappingTarget Lotacao lotacao);
}