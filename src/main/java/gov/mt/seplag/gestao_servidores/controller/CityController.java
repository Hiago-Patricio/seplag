package gov.mt.seplag.gestao_servidores.controller;

import gov.mt.seplag.gestao_servidores.dto.city.CityDTO;
import gov.mt.seplag.gestao_servidores.dto.city.CityPatchDTO;
import gov.mt.seplag.gestao_servidores.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("api/cities")
@RequiredArgsConstructor
public class CityController {
    private final CityService cityService;

    @GetMapping
    public List<CityDTO> getAllCities() {
        return cityService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityDTO> getCityById(@PathVariable Long id) {
        CityDTO city = cityService.findCityById(id);

        return ResponseEntity.ok(city);
    }

    @PostMapping
    public CityDTO createCity(@Valid @RequestBody CityDTO city) {
        return cityService.createCity(city);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CityDTO> updateCity(@PathVariable Long id, @Valid @RequestBody CityDTO cityDTO) {
        CityDTO updatedCity = cityService.updateCity(id, cityDTO);
        return ResponseEntity.ok(updatedCity);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CityDTO> patchCity(@PathVariable Long id, @Valid @RequestBody CityPatchDTO city) {
        CityDTO updatedCity = cityService.patchCity(id, city);
        return ResponseEntity.ok(updatedCity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable Long id) {
        cityService.deleteCity(id);
        return ResponseEntity.noContent().build();
    }
}
