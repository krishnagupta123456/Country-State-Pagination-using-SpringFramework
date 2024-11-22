package com.country_pagination.Service;

import com.country_pagination.Model.Country;
import com.country_pagination.Repository.CountryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CountryService {
    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public Country addCountry(Country country){
        return countryRepository.save(country);
    }
    public List<Country> getAllCountry(){
        return countryRepository.findAll();
    }
    public Page<Country> getCountries(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return countryRepository.findAll(pageable);
    }
    public Country getCountry(long id){
        return countryRepository.findById(id).get();
    }
    public Country updateCountry(long id, Country country){
        Country country1 = getCountry(id);
        if(country1!=null){
            country1.setName(country.getName());
            countryRepository.save(country1);
            return country1;
        }
        return null;
    }

    public boolean deleteCountry(long id) {
        if (countryRepository.existsById(id)) {
            countryRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
