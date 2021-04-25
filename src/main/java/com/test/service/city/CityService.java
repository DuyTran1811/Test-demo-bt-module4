package com.test.service.city;

import com.test.model.City;
import com.test.repository.ICityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CityService implements ICityService {
    private ICityRepository cityRepository;

    @Autowired
    public CityService(ICityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public Iterable<City> findAll() {
       return cityRepository.findAll();
    }

    @Override
    public City save(City city) {
        return cityRepository.save(city);
    }

    @Override
    public City findById(Integer id) {
        return cityRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Integer id) {
        cityRepository.deleteById(id);
    }

    @Override
    public Iterable<City> searchByName(String name) {
        return cityRepository.searchByName(name);
    }
}
