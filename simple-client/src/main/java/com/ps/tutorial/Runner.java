package com.ps.tutorial;

import com.ps.tutorial.model.country.Country;
import com.ps.tutorial.clients.CountryClient;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Runner {

    private static Logger log = Logger.getLogger(Runner.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ClientConfig.class);
        CountryClient client = ctx.getBean(CountryClient.class);
        Country country = client.getCountry("Ukraine");
        log.debug(country);
    }

}
