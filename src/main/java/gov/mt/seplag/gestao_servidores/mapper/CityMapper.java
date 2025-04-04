package gov.mt.seplag.gestao_servidores.mapper;

import gov.mt.seplag.gestao_servidores.dto.CityDTO;
import gov.mt.seplag.gestao_servidores.entity.City;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CityMapper {
    CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);

    @Mapping(source = "name", target="name")
    @Mapping(source = "uf", target="uf")
    CityDTO toDTO(City city);

    @Mapping(source = "name", target="name")
    @Mapping(source = "uf", target="uf")
    City toEntity(CityDTO cityDTO);
    List<CityDTO> toDTOList(List<City> cities);
}
