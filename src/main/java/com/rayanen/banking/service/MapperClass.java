package com.rayanen.banking.service;

import java.lang.reflect.Method;


public class MapperClass {
    public static Object mapper(Object entityObject, Object objectDto) {


        Class DtoClass = entityObject.getClass();

        Method[] declaredDtoMethods = DtoClass.getDeclaredMethods();

        for (Method method : declaredDtoMethods) {

            if (method.getName().startsWith("get")) {

                try {
                    method.invoke(entityObject);
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }
        return entityObject;
    }
}

