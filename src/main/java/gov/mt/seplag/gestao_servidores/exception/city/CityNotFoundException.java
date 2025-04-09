package gov.mt.seplag.gestao_servidores.exception.city;

import gov.mt.seplag.gestao_servidores.entity.City;
import gov.mt.seplag.gestao_servidores.exception.ResourceNotFoundException;

public class CityNotFoundException extends ResourceNotFoundException {
    public CityNotFoundException(Long id) {
        super(City.class, "id", id);
    }
}
