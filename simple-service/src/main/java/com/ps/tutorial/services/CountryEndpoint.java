package com.ps.tutorial.services;

import com.ps.tutorial.model.country.GetCountryRequest;
import com.ps.tutorial.model.country.GetCountryResponse;
import com.ps.tutorial.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CountryEndpoint {

    public static final String NAMESPACE_URI = "http://ps.com/tutorial/model/country";

    private CountryRepository repository;

    @Autowired
    public CountryEndpoint(CountryRepository repository) {
        this.repository = repository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
        GetCountryResponse response = new GetCountryResponse();
        response.setCountry(repository.findCountry(request.getName()));
        return response;
    }

}
