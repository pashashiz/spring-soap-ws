package com.ps.tutorial.clients;

import com.ps.tutorial.ClientConfig;
import com.ps.tutorial.model.country.Country;
import com.ps.tutorial.model.country.Currency;
import com.ps.tutorial.model.country.GetCountryRequest;
import com.ps.tutorial.model.country.GetCountryResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.test.client.MockWebServiceServer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.util.JAXBSource;

import static org.junit.Assert.assertEquals;
import static org.springframework.ws.test.client.RequestMatchers.*;
import static org.springframework.ws.test.client.ResponseCreators.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ClientConfig.class})
public class CountryClientTest {

    @Autowired
    private CountryClient client;

    private MockWebServiceServer mockServer;

    @Before
    public void createServer() throws Exception {
        mockServer = MockWebServiceServer.createServer(client);
    }

    @Test
    public void testGetCountry() throws JAXBException {
        // Prepare data
        Country ukraine = new Country();
        ukraine.setName("Ukraine");
        ukraine.setCapital("Kiyv");
        ukraine.setCurrency(Currency.UAH);
        ukraine.setPopulation(48186860);
        GetCountryRequest request = new GetCountryRequest();
        request.setName("Ukraine");
        GetCountryResponse response = new GetCountryResponse();
        response.setCountry(ukraine);
        // Prepare mock server
        mockServer.expect(payload(getSource(GetCountryRequest.class, request)))
                .andRespond(withPayload(getSource(GetCountryResponse.class, response)));
        // Test
        assertEquals(client.getCountry("Ukraine").getCapital(), "Kiyv");

    }

    private <T> JAXBSource getSource(Class<T> clazz, T request) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        return new JAXBSource(jaxbContext, request);
    }

}
