package gov.mt.seplag.gestao_servidores.mapper;

import gov.mt.seplag.gestao_servidores.dto.city.CityDTO;
import gov.mt.seplag.gestao_servidores.dto.city.CreateCityDTO;
import gov.mt.seplag.gestao_servidores.dto.city.UpdateCityDTO;
import gov.mt.seplag.gestao_servidores.entity.City;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CityMapper {
    @Mapping(target = "name", source = "name")
    @Mapping(target = "uf", source = "uf")
    CityDTO toCityDTO(City city);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "uf", source = "uf")
    City toEntity(CreateCityDTO cityDTO);

    List<CityDTO> toDTOList(List<City> cities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(UpdateCityDTO updateCityDTO, @MappingTarget City city);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(CreateCityDTO updateCityDTO, @MappingTarget City city);
}
