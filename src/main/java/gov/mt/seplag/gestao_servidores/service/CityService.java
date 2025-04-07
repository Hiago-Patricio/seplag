package gov.mt.seplag.gestao_servidores.service;

import gov.mt.seplag.gestao_servidores.dto.CityDTO;
import gov.mt.seplag.gestao_servidores.entity.City;
import gov.mt.seplag.gestao_servidores.mapper.CityMapper;
import gov.mt.seplag.gestao_servidores.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository repository;
    private final CityMapper mapper;

    public List<CityDTO> findAll() {
        log.info("Fetching all cities");
        List<CityDTO> cities = mapper.toDTOList(repository.findAll());
        log.debug("Found {} cities", cities.size());
        return cities;
    }

    public Optional<CityDTO> findCityById(Long id) {
        log.info("Fetching city with ID: {}", id);
        Optional<CityDTO> cityDTO = repository.findById(id)
                .map(mapper::toDTO);
        if (cityDTO.isPresent()) {
            log.debug("City found: {}", cityDTO.get());
        } else {
            log.warn("City not found with ID: {}", id);
        }
        return cityDTO;
    }

    public CityDTO createCity(CityDTO cityDTO) {
        log.info("Creating city: {}", cityDTO);
        City savedCity = repository.save(mapper.toEntity(cityDTO));
        log.debug("City created with ID: {}", savedCity.getId());
        return mapper.toDTO(savedCity);
    }

    public void deleteCity(Long id) {
        log.info("Deleting city with ID: {}", id);
        if (repository.existsById(id)) {
            repository.deleteById(id);
            log.debug("City deleted successfully with ID: {}", id);
        } else {
            log.error("City not found with ID: {}", id);
            throw new RuntimeException("City not found with id: " + id);
        }
    }
}
