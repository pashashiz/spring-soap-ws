package com.ps.tutorial;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;
import org.springframework.xml.xsd.XsdSchemaCollection;
import org.springframework.xml.xsd.commons.CommonsXsdSchemaCollection;

import java.io.IOException;

@EnableWs
@Configuration
@ComponentScan({"com.ps.tutorial.repository", "com.ps.tutorial.services"})
public class WSConfig {

    @Bean(name = "country")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchemaCollection countrySchemas) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("CountriesPort");
        wsdl11Definition.setLocationUri("/country");
        wsdl11Definition.setTargetNamespace("http://ps.com/tutorial/model/country");
        wsdl11Definition.setSchemaCollection(countrySchemas);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchemaCollection countrySchemas() {
        // Should be correct order: currency, country (because country imports currency)
        return new CommonsXsdSchemaCollection(new Resource[] {
                new ClassPathResource("schemes/currency.xsd"), new ClassPathResource("schemes/ws/country.xsd")
        });
    }

//    @Bean
//    public XsdSchema countrySchema() {
//        return new SimpleXsdSchema(new ClassPathResource("schemes/ws/country.xsd"));
//    }
//
//    @Bean
//    public XsdSchema currencySchema() {
//        return new SimpleXsdSchema(new ClassPathResource("schemes/currency.xsd"));
//    }

}
