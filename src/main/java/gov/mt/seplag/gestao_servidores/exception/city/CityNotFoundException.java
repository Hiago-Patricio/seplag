package gov.mt.seplag.gestao_servidores.exception.city;

import gov.mt.seplag.gestao_servidores.entity.City;
import gov.mt.seplag.gestao_servidores.exception.NotFoundException;

public class CityNotFoundException extends NotFoundException {
    public CityNotFoundException(Long id) {
        super(City.class, "id", id);
    }
}
