package com.ps.tutorial;

import com.ps.tutorial.interceptors.GlobalInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.server.endpoint.interceptor.PayloadLoggingInterceptor;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;
import org.springframework.xml.xsd.XsdSchemaCollection;
import org.springframework.xml.xsd.commons.CommonsXsdSchemaCollection;

import java.io.IOException;
import java.util.List;

@EnableWs
@Configuration
@ComponentScan({"com.ps.tutorial.repository", "com.ps.tutorial.services"})
public class WSConfig extends WsConfigurerAdapter {

    @Override
    public void addInterceptors(List<EndpointInterceptor> interceptors) {
        // Custom interceptor
        interceptors.add(new GlobalInterceptor());
        // Logging interceptor
        interceptors.add(new PayloadLoggingInterceptor());
    }

    @Bean(name = "country")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchemaCollection countrySchemas) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("CountriesPort");
        wsdl11Definition.setLocationUri("/country");
        wsdl11Definition.setTargetNamespace("http://ps.com/tutorial/model/country");
        //wsdl11Definition.setSchema(new SimpleXsdSchema(new ClassPathResource("schemes/ws/country.xsd")));
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

}
