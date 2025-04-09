package gov.mt.seplag.gestao_servidores.controller;

import gov.mt.seplag.gestao_servidores.dto.city.CityDTO;
import gov.mt.seplag.gestao_servidores.dto.city.CreateCityDTO;
import gov.mt.seplag.gestao_servidores.dto.city.UpdateCityDTO;
import gov.mt.seplag.gestao_servidores.service.CityService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("api/cities")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class CityController {
    private final CityService cityService;

    @GetMapping
    public ResponseEntity<List<CityDTO>> getAllCities(
            @ParameterObject
            @Parameter(
                    description = "Parameters of pagination",
                    examples = {
                            @io.swagger.v3.oas.annotations.media.ExampleObject(name = "Pagination example", value= "page=0&size=10&sort=name,asc")
                    }
            )
            Pageable pageable) {
        Page<CityDTO> cities = cityService.findAll(pageable);
        return ResponseEntity.ok(cities.getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityDTO> getCityById(@PathVariable Long id) {
        CityDTO city = cityService.findCityById(id);

        return ResponseEntity.ok(city);
    }

    @PostMapping
    public CityDTO createCity(@Valid @RequestBody CreateCityDTO city) {
        return cityService.createCity(city);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CityDTO> updateCity(@PathVariable Long id, @Valid @RequestBody UpdateCityDTO city) {
        CityDTO updatedCity = cityService.updateCity(id, city);
        return ResponseEntity.ok(updatedCity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable Long id) {
        cityService.deleteCity(id);
        return ResponseEntity.noContent().build();
    }
}
