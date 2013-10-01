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

    /**
     * {@inheritDoc}
     */

    public String insert(int id, String name) {
        return String.format("insert id=%d and name=%s", id, name);
    }

    /**
     * {@inheritDoc}
     */

    public String update(int id, String name) {
        return String.format("update id=%d and name=%s", id, name);
    }

    /**
     * {@inheritDoc}
     */

    public String delete(int id) {
        return String.format("delete id=%d", id);
    }
}
