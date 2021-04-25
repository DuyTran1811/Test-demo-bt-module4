package com.test.service.country;

import com.test.model.Country;
import com.test.repository.ICountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CountryService implements ICountryService {
    private ICountryRepository countryRepository;

    @Autowired
    public CountryService(ICountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Iterable<Country> findAll() {
      return countryRepository.findAll();
    }

    @Override
    public Country save(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public Country findById(Integer id) {
        return countryRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Integer id) {
        countryRepository.deleteById(id);
    }
}
