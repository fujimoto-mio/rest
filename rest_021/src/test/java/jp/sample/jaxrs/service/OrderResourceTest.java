package jp.sample.jaxrs.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jettison.AbstractXMLStreamReader;
import org.codehaus.jettison.json.JSONObject;
import org.codehaus.jettison.mapped.MappedXMLStreamReader;
import org.junit.Assert;
import org.junit.Test;

public class OrderResourceTest {

    @Test
    public void testOrderItem01() {
        Order order = new Order();
        order.setOrderNo(1L);
        order.setUserName("Tarou");
        order.setItemName("item01");

        WebClient client =
                WebClient
                        .create("http://localhost:8080/rest/order/create")
                        .type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        Response actual = client.post(order);

        try {
            String result = IOUtils.readStringFromStream((InputStream) actual.getEntity());
            JSONObject json = new JSONObject(result);
            AbstractXMLStreamReader reader = new MappedXMLStreamReader(json);
            reader.next();
            reader.next();
            String orderNo = reader.getText();

            Assert.assertEquals(Status.OK.getStatusCode(), actual.getStatus());
            Assert.assertEquals(
                    "{\"order\":{\"orderNo\":" + orderNo
                            + ",\"userName\":\"Tarou\",\"itemName\":\"item01\"}}",
                    result);
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void testGetOrderItem01() {
        WebClient client =
                WebClient
                        .create("http://localhost:8080/rest/order/get/1")
                        .type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        Response actual = client.get();

        try {
            Assert.assertEquals(Status.OK.getStatusCode(), actual.getStatus());
            Assert.assertEquals(
                    "{\"order\":{\"orderNo\":1,\"userName\":\"Tarou\",\"itemName\":\"item01\"}}",
                    IOUtils.readStringFromStream((InputStream) actual.getEntity()));
        } catch (IOException e) {
            Assert.fail();
        }
    }

    @Test
    public void testUpdateOrderItem01() {
        Order order = new Order();
        order.setOrderNo(1L);
        order.setUserName("Tarou Yamada");
        order.setItemName("item02");

        WebClient client =
                WebClient
                        .create("http://localhost:8080/rest/order/update")
                        .type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        Response actual = client.put(order);

        try {
            Assert.assertEquals(Status.OK.getStatusCode(), actual.getStatus());
            Assert.assertEquals(
                    "{\"order\":{\"orderNo\":1,\"userName\":\"Tarou Yamada\",\"itemName\":\"item02\"}}",
                    IOUtils.readStringFromStream((InputStream) actual.getEntity()));
        } catch (IOException e) {
            Assert.fail();
        }

        order.setUserName("Tarou");
        order.setItemName("item01");

        actual = client.put(order);

        try {
            Assert.assertEquals(Status.OK.getStatusCode(), actual.getStatus());
            Assert.assertEquals(
                    "{\"order\":{\"orderNo\":1,\"userName\":\"Tarou\",\"itemName\":\"item01\"}}",
                    IOUtils.readStringFromStream((InputStream) actual.getEntity()));
        } catch (IOException e) {
            Assert.fail();
        }
    }

    @Test
    public void testCancelOrderItem01() {
        WebClient client =
                WebClient
                        .create("http://localhost:8080/rest/order/cancel/1")
                        .type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        Response actual = client.delete();

        Date orderDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+09:00");

        try {
            Assert.assertEquals(Status.OK.getStatusCode(), actual.getStatus());
            Assert.assertEquals(
                    "{\"order\":{\"orderNo\":1,\"userName\":\"Tarou\",\"itemName\":\"item01\",\"orderDate\":\""
                            + format.format(orderDate) + "\"}}",
                    IOUtils.readStringFromStream((InputStream) actual.getEntity()));
        } catch (IOException e) {
            Assert.fail();
        }
    }

    @Test
    public void testShowOrderHistory() {
        WebClient client =
                WebClient
                        .create("http://localhost:8080/rest/order/history")
                        .type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        Response actual = client.get();

        try {
            Assert.assertEquals(Status.OK.getStatusCode(), actual.getStatus());
        } catch (Exception e) {
            Assert.fail();
        }
    }
}