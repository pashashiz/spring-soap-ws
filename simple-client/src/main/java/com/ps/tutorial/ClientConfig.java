package com.ps.tutorial;

import com.ps.tutorial.clients.CountryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class ClientConfig {

    @Bean public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setPackagesToScan("com.ps.tutorial.model");
        return marshaller;
    }

    @Bean public CountryClient countryClient(Jaxb2Marshaller marshaller){
        CountryClient client = new CountryClient();
        client.setDefaultUri("http://localhost:8080/simple-service/country");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

}
