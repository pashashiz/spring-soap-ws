package com.ps.tutorial.interceptors;

import org.apache.log4j.Logger;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.EndpointInterceptor;

public class GlobalInterceptor implements EndpointInterceptor {

    private Logger log = Logger.getLogger(getClass());

    @Override
    public boolean handleRequest(MessageContext messageContext, Object endpoint) throws Exception {
        // endpoint argument represents MethodEndpoint object: {beanName, method}
        //log.debug("Interceptor: {endpoint: " + endpoint + ", request: "+ messageContext.getRequest() + "}");
        return true;
    }

    @Override
    public boolean handleResponse(MessageContext messageContext, Object endpoint) throws Exception {
        //log.debug("Interceptor: {endpoint: " + endpoint + ", response: "+ messageContext.getResponse() + "}");
        return true;
    }

    @Override
    public boolean handleFault(MessageContext messageContext, Object endpoint) throws Exception {
        //log.debug("Interceptor: {endpoint: " + endpoint + ", response with fault: "+ messageContext.getResponse() + "}");
        return true;
    }

    @Override
    public void afterCompletion(MessageContext messageContext, Object endpoint, Exception e) throws Exception {
        //log.debug("Interceptor after completion: {endpoint: " + endpoint + "}");
    }

}
