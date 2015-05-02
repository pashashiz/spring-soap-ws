package com.ps.tutorial.repository;

import com.ps.tutorial.model.country.Country;
import com.ps.tutorial.model.currency.Currency;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class CountryRepository {

    private static final List<Country> countries = new ArrayList<Country>();

    @PostConstruct
    public void initData() {
        Country ukraine = new Country();
        ukraine.setName("Ukraine");
        ukraine.setCapital("Kiyv");
        ukraine.setCurrency(Currency.UAH);
        ukraine.setPopulation(48186860);
        Country spain = new Country();
        spain.setName("Spain");
        spain.setCapital("Madrid");
        spain.setCurrency(Currency.EUR);
        spain.setPopulation(46704314);
        Country usa = new Country();
        usa.setName("USA");
        usa.setCapital("Washington");
        usa.setCurrency(Currency.USD);
        usa.setPopulation(230705000);
        countries.add(ukraine);
        countries.add(spain);
        countries.add(usa);
    }

    public Country findCountry(String name) {
        Assert.notNull(name);
        Country result = null;
        for (Country country : countries) {
            if (name.equals(country.getName())) {
                result = country;
            }
        }
        return result;
    }

}
