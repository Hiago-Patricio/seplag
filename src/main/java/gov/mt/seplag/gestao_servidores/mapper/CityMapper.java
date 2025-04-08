package gov.mt.seplag.gestao_servidores.mapper;

import gov.mt.seplag.gestao_servidores.dto.CityDTO;
import gov.mt.seplag.gestao_servidores.entity.City;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CityMapper {
    @Mapping(target = "name", source = "name")
    @Mapping(target = "uf", source = "uf")
    CityDTO toDTO(City city);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "uf", source = "uf")
    City toEntity(CityDTO cityDTO);
    List<CityDTO> toDTOList(List<City> cities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(CityDTO cityDTO, @MappingTarget City city);
}
