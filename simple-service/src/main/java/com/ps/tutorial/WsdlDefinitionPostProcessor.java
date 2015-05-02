package com.ps.tutorial;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.ws.transport.http.WsdlDefinitionHandlerAdapter;

public class WsdlDefinitionPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof WsdlDefinitionHandlerAdapter) {
            ((WsdlDefinitionHandlerAdapter) bean).setTransformLocations(true);
            ((WsdlDefinitionHandlerAdapter) bean).setTransformSchemaLocations(true);
        }
        return bean;
    }
}
