package com.bring.project.bring.model.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
class BookTest {
    private static final Integer ID = 1;
    private static final Date TIME = new Date();
    private static final String TEST_STRING = "STRING";
    private static final Integer TEST_STOCK = 15;
    private static final Double TEST_UNIT_PRICE = 2.5d;
    private static final String FIELDS_NOT_MATCH = "Fields did not match";
    private static final String FIELDS_WAS_NOT_RETRIEVED = "field was not retrieved properly";
    Book pojo;

    @BeforeEach
    void setUp() {
        pojo = new Book();
    }

    @Test
    void getId() throws NoSuchFieldException, IllegalAccessException {
        final Field field = pojo.getClass().getDeclaredField("id");
        field.setAccessible(true);
        field.set(pojo, ID);

        //when
        final Integer result = pojo.getId();

        //then
        assertEquals(result, ID, FIELDS_WAS_NOT_RETRIEVED);
    }

    @Test
    void setId() throws NoSuchFieldException, IllegalAccessException {
        //when
        pojo.setId(ID);

        //then
        final Field field = pojo.getClass().getDeclaredField("id");
        field.setAccessible(true);
        assertEquals(field.get(pojo), ID, FIELDS_NOT_MATCH);
    }

    @Test
    void getBookName() throws NoSuchFieldException, IllegalAccessException {
        final Field field = pojo.getClass().getDeclaredField("bookName");
        field.setAccessible(true);
        field.set(pojo, TEST_STRING);

        //when
        final String result = pojo.getBookName();

        //then
        assertEquals(result, TEST_STRING, FIELDS_WAS_NOT_RETRIEVED);
    }

    @Test
    void setBookName() throws NoSuchFieldException, IllegalAccessException {
        //when
        pojo.setBookName(TEST_STRING);

        //then
        final Field field = pojo.getClass().getDeclaredField("bookName");
        field.setAccessible(true);
        assertEquals(field.get(pojo), TEST_STRING, FIELDS_NOT_MATCH);
    }

    @Test
    void getWriter() throws NoSuchFieldException, IllegalAccessException {
        final Field field = pojo.getClass().getDeclaredField("writer");
        field.setAccessible(true);
        field.set(pojo, TEST_STRING);

        //when
        final String result = pojo.getWriter();

        //then
        assertEquals(result, TEST_STRING, FIELDS_WAS_NOT_RETRIEVED);
    }

    @Test
    void setWriter() throws NoSuchFieldException, IllegalAccessException {
        //when
        pojo.setWriter(TEST_STRING);

        //then
        final Field field = pojo.getClass().getDeclaredField("writer");
        field.setAccessible(true);
        assertEquals(field.get(pojo), TEST_STRING, FIELDS_NOT_MATCH);
    }

    @Test
    void getCategory() throws IllegalAccessException, NoSuchFieldException {
        final Field field = pojo.getClass().getDeclaredField("category");
        field.setAccessible(true);
        field.set(pojo, TEST_STRING);

        //when
        final String result = pojo.getCategory();

        //then
        assertEquals(result, TEST_STRING, FIELDS_WAS_NOT_RETRIEVED);
    }

    @Test
    void setCategory() throws NoSuchFieldException, IllegalAccessException {
        //when
        pojo.setCategory(TEST_STRING);

        //then
        final Field field = pojo.getClass().getDeclaredField("category");
        field.setAccessible(true);
        assertEquals(field.get(pojo), TEST_STRING, FIELDS_NOT_MATCH);
    }

    @Test
    void getQuantityStock() throws NoSuchFieldException, IllegalAccessException {
        final Field field = pojo.getClass().getDeclaredField("quantityStock");
        field.setAccessible(true);
        field.set(pojo, TEST_STOCK);

        //when
        final Integer result = pojo.getQuantityStock();

        //then
        assertEquals(result, TEST_STOCK, FIELDS_WAS_NOT_RETRIEVED);
    }

    @Test
    void setQuantityStock() throws NoSuchFieldException, IllegalAccessException {
        //when
        pojo.setQuantityStock(TEST_STOCK);

        //then
        final Field field = pojo.getClass().getDeclaredField("quantityStock");
        field.setAccessible(true);
        assertEquals(field.get(pojo), TEST_STOCK, FIELDS_NOT_MATCH);
    }

    @Test
    void getOrders() throws IllegalAccessException, NoSuchFieldException {
        Order order = new Order();
        List<Order> list = new ArrayList<>();
        list.add(order);
        final Field field = pojo.getClass().getDeclaredField("orders");
        field.setAccessible(true);
        field.set(pojo, list);

        //when
        final List<Order> result = pojo.getOrders();

        //then
        assertEquals(result, list, FIELDS_WAS_NOT_RETRIEVED);
    }

    @Test
    void setOrders() throws NoSuchFieldException, IllegalAccessException {
        Order order = new Order();
        List<Order> list = new ArrayList<>();
        list.add(order);
        //when
        pojo.setOrders(list);

        //then
        final Field field = pojo.getClass().getDeclaredField("orders");
        field.setAccessible(true);
        assertEquals(field.get(pojo), list, FIELDS_NOT_MATCH);
    }

    @Test
    void getUnitPrice() throws NoSuchFieldException, IllegalAccessException {
        final Field field = pojo.getClass().getDeclaredField("unitPrice");
        field.setAccessible(true);
        field.set(pojo, TEST_UNIT_PRICE);

        //when
        final Double result = pojo.getUnitPrice();

        //then
        assertEquals(result, TEST_UNIT_PRICE, FIELDS_WAS_NOT_RETRIEVED);
    }

    @Test
    void setUnitPrice() throws NoSuchFieldException, IllegalAccessException {
        //when
        pojo.setUnitPrice(TEST_UNIT_PRICE);

        //then
        final Field field = pojo.getClass().getDeclaredField("unitPrice");
        field.setAccessible(true);
        assertEquals(field.get(pojo), TEST_UNIT_PRICE, FIELDS_NOT_MATCH);
    }
}