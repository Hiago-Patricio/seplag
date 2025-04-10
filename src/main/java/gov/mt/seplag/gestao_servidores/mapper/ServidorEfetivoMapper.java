package gov.mt.seplag.gestao_servidores.mapper;

import gov.mt.seplag.gestao_servidores.dto.servidor_efetivo.ServidorEfetivoRequestDTO;
import gov.mt.seplag.gestao_servidores.dto.servidor_efetivo.ServidorEfetivoResponseDTO;
import gov.mt.seplag.gestao_servidores.entity.Pessoa;
import gov.mt.seplag.gestao_servidores.entity.ServidorEfetivo;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ServidorEfetivoMapper {

    @Mapping(source = "pessoa", target = "pessoa")
    ServidorEfetivoResponseDTO toServidorEfetivoResponseDTO(ServidorEfetivo servidorEfetivo);

    @Mapping(target = "pessoa", source = "pessoa")
    ServidorEfetivo toServidorEfetivoEntity(ServidorEfetivoRequestDTO servidorEfetivoRequestDTO);

    @Mapping(target = "pessoa", source = "pessoa")
    void updateServidorEfetivoFromDTO(ServidorEfetivoRequestDTO servidorEfetivoRequestDTO, @MappingTarget ServidorEfetivo servidorEfetivo, Pessoa pessoa);
}