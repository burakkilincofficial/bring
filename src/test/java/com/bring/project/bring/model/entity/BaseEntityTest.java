package com.bring.project.bring.model.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Field;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
class BaseEntityTest {
    private static final Integer ID = 1;
    private static final Date TIME = new Date();
    private static final String FIELDS_NOT_MATCH = "Fields did not match";
    private static final String FIELDS_WAS_NOT_RETRIEVED = "field was not retrieved properly";
    BaseEntity pojo;

    @BeforeEach
    void setUp() {
        pojo = new BaseEntity();
    }

    @Test
    void getCreatedDate() throws NoSuchFieldException, IllegalAccessException {
        final Field field = pojo.getClass().getDeclaredField("createdDate");
        field.setAccessible(true);
        field.set(pojo, TIME);

        //when
        final Date result = pojo.getCreatedDate();

        //then
        assertEquals(result, TIME, FIELDS_WAS_NOT_RETRIEVED);
    }

    @Test
    void getLastModifiedDate() throws NoSuchFieldException, IllegalAccessException {
        final Field field = pojo.getClass().getDeclaredField("lastModifiedDate");
        field.setAccessible(true);
        field.set(pojo, TIME);

        //when
        final Date result = pojo.getLastModifiedDate();

        //then
        assertEquals(result, TIME, FIELDS_WAS_NOT_RETRIEVED);
    }

    @Test
    void setCreatedDate() throws NoSuchFieldException, IllegalAccessException {
        //when
        pojo.setCreatedDate(TIME);

        //then
        final Field field = pojo.getClass().getDeclaredField("createdDate");
        field.setAccessible(true);
        assertEquals(field.get(pojo), TIME, FIELDS_NOT_MATCH);
    }

    @Test
    void setLastModifiedDate() throws NoSuchFieldException, IllegalAccessException {
        //when
        pojo.setLastModifiedDate(TIME);

        //then
        final Field field = pojo.getClass().getDeclaredField("lastModifiedDate");
        field.setAccessible(true);
        assertEquals(field.get(pojo), TIME, FIELDS_NOT_MATCH);
    }

    @Test
    void testEquals() {
        BaseEntity baseEntity = BaseEntity.builder()
                .createdDate(TIME)
                .lastModifiedDate(TIME)
                .build();
        BaseEntity baseEntity1 = BaseEntity.builder()
                .createdDate(TIME)
                .lastModifiedDate(TIME)
                .build();
        assertEquals(baseEntity, baseEntity1);
    }

    @Test
    void testHashCode() {
        BaseEntity baseEntity = BaseEntity.builder()
                .createdDate(TIME)
                .lastModifiedDate(TIME)
                .build();
        BaseEntity baseEntity1 = BaseEntity.builder()
                .createdDate(TIME)
                .lastModifiedDate(TIME)
                .build();
        assertEquals(baseEntity.hashCode(), baseEntity1.hashCode());
    }
}