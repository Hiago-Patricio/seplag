package gov.mt.seplag.gestao_servidores.service;

import gov.mt.seplag.gestao_servidores.dto.city.CityDTO;
import gov.mt.seplag.gestao_servidores.dto.city.CityPatchDTO;
import gov.mt.seplag.gestao_servidores.entity.City;
import gov.mt.seplag.gestao_servidores.exception.city.CityNotFoundException;
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

    public CityDTO findCityById(Long id) {
        log.info("Fetching city with ID: {}", id);
        Optional<City> cityOpt = repository.findById(id);

        if (cityOpt.isPresent()) {
            CityDTO cityDTO = mapper.toDTO(cityOpt.get());
            log.debug("City found: {}", cityDTO);
            return cityDTO;
        } else {
            log.warn("City not found with ID: {}", id);
            throw new CityNotFoundException(id);
        }
    }

    public CityDTO createCity(CityDTO cityDTO) {
        log.info("Creating city: {}", cityDTO);
        City savedCity = repository.save(mapper.toEntity(cityDTO));
        log.debug("City created with ID: {}", savedCity.getId());
        return mapper.toDTO(savedCity);
    }

    public CityDTO updateCity(Long id, CityDTO cityDTO) {
        log.info("Updating city with ID: {} - {}", id, cityDTO);

        City existingCity = repository.findById(id)
                .orElseThrow(() -> new CityNotFoundException(id));

        mapper.updateEntityFromDTO(cityDTO, existingCity);

        City updatedCity = repository.save(existingCity);
        log.debug("City updated: {}", updatedCity);

        return mapper.toDTO(updatedCity);
    }

    public CityDTO patchCity(Long id, CityPatchDTO cityPatchDTO) {
        log.info("Patching city with ID: {} - {}", id, cityPatchDTO);

        City existingCity = repository.findById(id)
                .orElseThrow(() -> new CityNotFoundException(id));

        mapper.patchEntiyFromDTO(cityPatchDTO, existingCity);

        City updatedCity = repository.save(existingCity);
        log.debug("City patched: {}", updatedCity);

        return mapper.toDTO(updatedCity);
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
