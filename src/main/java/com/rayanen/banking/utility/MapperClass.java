package com.rayanen.banking.utility;

import com.rayanen.banking.utility.Annotations.MapTo;
import com.rayanen.banking.utility.Annotations.NotMap;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class MapperClass {

    public static <E, D> E dtoToEntityMapper(E entityObject, D dtoObject) {


        Class dtoClass = dtoObject.getClass();

        Method[] dtoMethodsArray = dtoClass.getMethods();

        ArrayList<Method> dtoMethods = new ArrayList<>(Arrays.asList(dtoMethodsArray));
        ArrayList<Method> removalMethods = new ArrayList<>();
        for (Method dtoMethod : dtoMethods) {
            if (!dtoMethod.getName().contains("get")) {
                removalMethods.add(dtoMethod);
            }


        }

        dtoMethods.removeAll(removalMethods);

        Field[] dtoFieldNamesArray = dtoClass.getDeclaredFields();

        ArrayList<Field> dtoFieldNames = new ArrayList(Arrays.asList(dtoFieldNamesArray));

        dtoFieldNamesArray = dtoObject.getClass().getSuperclass().getDeclaredFields();

        dtoFieldNames .addAll(new ArrayList(Arrays.asList(dtoFieldNamesArray)));
//        fs[0].setAccessible(true);

        Class entityClass = entityObject.getClass();

        Method[] entityMethods = entityClass.getMethods();

        for (Field fieldName :dtoFieldNames) {

            for (Method dtoMethod : dtoMethods) {

                if (fieldName.getType().toString().contains("List") && Objects.nonNull(fieldName.getDeclaredAnnotation(NotMap.class))) {

                    for (Method declaredEntityMethod : entityMethods) {

                        if (declaredEntityMethod.getName().startsWith("set") && Objects.equals(declaredEntityMethod.getName(), "set" + dtoMethod.getName().substring(3))) {

                            try {

                                Object invokedMethod = dtoMethod.invoke(dtoObject);
                                declaredEntityMethod.invoke(entityObject, invokedMethod);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    }

                    break;

                } else if (fieldName.getType().toString().contains("List")) {
                    try {

                        List invokedMethod = (ArrayList)dtoMethod.invoke(dtoObject);
                        List mappedDtoList = new ArrayList();

                        for (Object invokedList : invokedMethod) {

                            mappedDtoList.add(MapperClass.dtoToEntityMapper(new Object() , invokedList));
                        }

                        for (Method declaredEntityMethod : entityMethods) {

                            if (declaredEntityMethod.getName().startsWith("set") && Objects.equals(declaredEntityMethod.getName(), "set" + dtoMethod.getName().substring(3))) {

                                try {

                                    declaredEntityMethod.invoke(entityObject, mappedDtoList);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;

                } else if (Objects.equals(fieldName.getName(), dtoMethod.getName().substring(3).toLowerCase()) && Objects.nonNull(fieldName.getDeclaredAnnotation(MapTo.class))) {

                    for (Method declaredEntityMethod : entityMethods) {

                        if (declaredEntityMethod.getName().startsWith("set") && Objects.equals(declaredEntityMethod.getName(), "set" + dtoMethod.getName().substring(3))) {

                            try {

                                Object invokedMethod = dtoMethod.invoke(dtoObject);
                                Object mappedDto = MapperClass.dtoToEntityMapper(new Object() , invokedMethod);
                                declaredEntityMethod.invoke(entityObject, mappedDto);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    }


                    break;

                } else {
                    for (Method declaredEntityMethod : entityMethods) {
//
                        if (declaredEntityMethod.getName().startsWith("set") && Objects.equals(declaredEntityMethod.getName(), "set" + dtoMethod.getName().substring(3))) {

                            try {

                                Object invokedMethod = dtoMethod.invoke(dtoObject);
                                declaredEntityMethod.invoke(entityObject, invokedMethod);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }


            }
        }


        return entityObject;
    }

    public static Object entityToDtoMapper(Object dtoObject, Object entityObject) {

        Class dtoClass = dtoObject.getClass();

        Method[] dtoMethods = dtoClass.getMethods();

        Class entityClass = entityObject.getClass();

        Method[] entityMethods = entityClass.getMethods();

        String methodName;

        for (Method declaredEntityMethod : entityMethods) {

            if (declaredEntityMethod.getName().startsWith("get")) {

                methodName = declaredEntityMethod.getName().substring(3);
                for (Method declaredDtoMethod : dtoMethods) {

                    if (declaredDtoMethod.getName().startsWith("set") && Objects.equals(declaredDtoMethod.getName(), "set" + methodName)) {

                        try {

                            Object invokedMethod = declaredEntityMethod.invoke(entityObject);
                            declaredDtoMethod.invoke(dtoObject, invokedMethod);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }


                }
            }

        }
        return dtoObject;
    }
}

