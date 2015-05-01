package com.ps.tutorial.clients;

import com.ps.tutorial.model.country.Country;
import com.ps.tutorial.model.country.GetCountryRequest;
import com.ps.tutorial.model.country.GetCountryResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class CountryClient extends WebServiceGatewaySupport {

    public Country getCountry(String name) {
        GetCountryRequest request = new GetCountryRequest();
        request.setName(name);
        GetCountryResponse response = (GetCountryResponse)getWebServiceTemplate().marshalSendAndReceive(request);
        return response.getCountry();
    }

}
