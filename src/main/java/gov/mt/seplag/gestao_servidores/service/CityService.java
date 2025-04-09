package gov.mt.seplag.gestao_servidores.service;

import gov.mt.seplag.gestao_servidores.dto.city.CreateCityDTO;
import gov.mt.seplag.gestao_servidores.dto.city.CityDTO;
import gov.mt.seplag.gestao_servidores.dto.city.UpdateCityDTO;
import gov.mt.seplag.gestao_servidores.entity.City;
import gov.mt.seplag.gestao_servidores.exception.city.CityNotFoundException;
import gov.mt.seplag.gestao_servidores.mapper.CityMapper;
import gov.mt.seplag.gestao_servidores.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository repository;
    private final CityMapper mapper;

    public Page<CityDTO> findAll(Pageable pageable) {
        log.info("Fetching all cities");

        Page<City> page = repository.findAll(pageable);
        Page<CityDTO> dtoPage = page.map(mapper::toCityDTO);

        log.debug("Found {} cities", page.getTotalElements());
        return dtoPage;
    }

    public CityDTO findCityById(Long id) {
        log.info("Fetching city with ID: {}", id);
        Optional<City> cityOpt = repository.findById(id);

        if (cityOpt.isPresent()) {
            CityDTO cityDTO = mapper.toCityDTO(cityOpt.get());
            log.debug("City found: {}", cityDTO);
            return cityDTO;
        } else {
            log.warn("City not found with ID: {}", id);
            throw new CityNotFoundException(id);
        }
    }

    public CityDTO createCity(CreateCityDTO city) {
        log.info("Creating city: {}", city);
        City savedCity = repository.save(mapper.toEntity(city));
        log.debug("City created with ID: {}", savedCity.getId());
        return mapper.toCityDTO(savedCity);
    }

    public CityDTO updateCity(Long id, UpdateCityDTO cityDTO) {
        log.info("Updating city with ID: {} - {}", id, cityDTO);

        City existingCity = repository.findById(id)
                .orElseThrow(() -> new CityNotFoundException(id));

        mapper.updateEntityFromDTO(cityDTO, existingCity);

        City updatedCity = repository.save(existingCity);
        log.debug("City updated: {}", updatedCity);

        return mapper.toCityDTO(updatedCity);
    }

    public void deleteCity(Long id) {
        log.info("Deleting city with ID: {}", id);
        if (repository.existsById(id)) {
            repository.deleteById(id);
            log.debug("City deleted successfully with ID: {}", id);
        } else {
            log.error("City not found with ID: {}", id);
            throw new CityNotFoundException(id);
        }
    }
}
