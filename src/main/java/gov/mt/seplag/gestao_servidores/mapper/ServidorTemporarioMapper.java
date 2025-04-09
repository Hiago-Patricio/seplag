package gov.mt.seplag.gestao_servidores.mapper;

import gov.mt.seplag.gestao_servidores.dto.servidor_temporario.ServidorTemporarioRequestDTO;
import gov.mt.seplag.gestao_servidores.dto.servidor_temporario.ServidorTemporarioResponseDTO;
import gov.mt.seplag.gestao_servidores.entity.Pessoa;
import gov.mt.seplag.gestao_servidores.entity.ServidorTemporario;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ServidorTemporarioMapper {

    @Mapping(source = "pessoa", target = "pessoa")
    ServidorTemporarioResponseDTO toServidorTemporarioResponseDTO(ServidorTemporario servidorTemporario);

    @Mapping(target = "pessoa", source = "pessoa")
    ServidorTemporario toServidorTemporarioEntity(ServidorTemporarioRequestDTO servidorTemporarioRequestDTO, Pessoa pessoa);

    @Mapping(target = "pessoa", source = "pessoa")
    void updateServidorTemporarioFromDTO(ServidorTemporarioRequestDTO servidorTemporarioRequestDTO, @MappingTarget ServidorTemporario servidorTemporario, Pessoa pessoa);
}