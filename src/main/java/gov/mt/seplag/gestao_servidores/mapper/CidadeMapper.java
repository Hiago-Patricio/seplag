package gov.mt.seplag.gestao_servidores.mapper;

import gov.mt.seplag.gestao_servidores.dto.cidade.CidadeDTO;
import gov.mt.seplag.gestao_servidores.dto.cidade.CreateCidadeDTO;
import gov.mt.seplag.gestao_servidores.dto.cidade.UpdateCidadeDTO;
import gov.mt.seplag.gestao_servidores.entity.Cidade;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CidadeMapper {
    @Mapping(target = "nome", source = "nome")
    @Mapping(target = "uf", source = "uf")
    CidadeDTO toCidadeDTO(Cidade cidade);

    @Mapping(target = "nome", source = "nome")
    @Mapping(target = "uf", source = "uf")
    Cidade toEntity(CreateCidadeDTO cidadeDTO);

    List<CidadeDTO> toDTOList(List<Cidade> cities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(UpdateCidadeDTO updateCidadeDTO, @MappingTarget Cidade cidade);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(CreateCidadeDTO updateCidadeDTO, @MappingTarget Cidade cidade);
}
