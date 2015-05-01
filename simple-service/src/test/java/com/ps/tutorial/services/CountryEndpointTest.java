package com.ps.tutorial.services;

import com.ps.tutorial.WSConfig;
import com.ps.tutorial.model.country.GetCountryRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.test.server.MockWebServiceClient;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.util.JAXBSource;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.ws.test.server.RequestCreators.*;
import static org.springframework.ws.test.server.ResponseMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WSConfig.class})
public class CountryEndpointTest {

    @Autowired
    private ApplicationContext applicationContext;

    private MockWebServiceClient mockClient;

    @Before
    public void createClient() {
        mockClient = MockWebServiceClient.createClient(applicationContext);
    }

    @Test
    public void getCountryTest() throws Exception {
        // Prepare request
        GetCountryRequest request = new GetCountryRequest();
        request.setName("Ukraine");
        // Prepare namespaces for x-path evaluations
        Map<String, String> ns = new HashMap<>();
        ns.put("cns", "http://ps.com/tutorial/model/country");
        // Perform request and evaluate response
        mockClient.sendRequest(withPayload(getSource(GetCountryRequest.class, request)))
                .andExpect(xpath("//cns:country/cns:name", ns).evaluatesTo("Ukraine"));
    }

    private <T> JAXBSource getSource(Class<T> clazz, T request) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        return new JAXBSource(jaxbContext, request);
    }

}
