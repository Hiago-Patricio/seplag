package gov.mt.seplag.gestao_servidores.service;

import gov.mt.seplag.gestao_servidores.dto.CityDTO;
import gov.mt.seplag.gestao_servidores.entity.City;
import gov.mt.seplag.gestao_servidores.mapper.CityMapper;
import gov.mt.seplag.gestao_servidores.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository repository;
    private final CityMapper mapper;

    public List<CityDTO> findAll() {
        return mapper.toDTOList(repository.findAll());
    }

    public Optional<CityDTO> findCityById(Long id) {
        return repository.findById(id)
                .map(mapper::toDTO);
    }

    public CityDTO createCity(CityDTO cityDTO) {
        City savedCity = repository.save(mapper.toEntity(cityDTO));

        return mapper.toDTO(savedCity);
    }

    public void deleteCity(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("City not found with id: " + id);
        }
    }
}
