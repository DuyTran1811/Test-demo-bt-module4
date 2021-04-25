package com.test.service.city;

import com.test.model.City;
import com.test.service.IGeneral;

public interface ICityService extends IGeneral<City> {
    Iterable<City> searchByName(String name);
}
