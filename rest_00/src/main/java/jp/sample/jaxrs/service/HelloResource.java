package jp.sample.jaxrs.service;

import org.springframework.stereotype.Component;

@Component
public class HelloResource implements SampleResource {

    /**
     * {@inheritDoc}
     */
    public String sayHello(String message) {
        return String.format("Hello, %s", message);
    }

}