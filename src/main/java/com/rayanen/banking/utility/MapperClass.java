package com.rayanen.banking.utility;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class MapperClass {

    public static Object dtoToEntityMapper(Object entityObject, Object dtoObject) {


        Class dtoClass = dtoObject.getClass();

        Method[] dtoMethods = dtoClass.getMethods();

        Class entityClass = entityObject.getClass();

        Method[] entityMethods = entityClass.getMethods();

        String methodName;

        for (Method declaredDtoMethod : dtoMethods) {

            if (declaredDtoMethod.getName().startsWith("get")) {
                methodName = declaredDtoMethod.getName().substring(3);
                if (declaredDtoMethod.getReturnType().toString().contains("List")) {
                    methodName = declaredDtoMethod.getName().substring(0 , declaredDtoMethod.getName().length()-4);
                    List entityList = new ArrayList();
                    try {

                        ArrayList invokedDtoList = (ArrayList) declaredDtoMethod.invoke(dtoObject);
                        for (Object dtoList : invokedDtoList) {

                            entityList.add(MapperClass.dtoToEntityMapper(new Object(), dtoList));

                        }
                        for (Method declaredEntityMethod : entityMethods) {

                            if (declaredEntityMethod.getName().startsWith("set") && Objects.equals(declaredEntityMethod.getName(), "set" + methodName)) {

                                try {

                                    declaredEntityMethod.invoke(entityObject, entityList);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {

                    for (Method declaredEntityMethod : entityMethods) {

                        if (declaredEntityMethod.getName().startsWith("set") && Objects.equals(declaredEntityMethod.getName(), "set" + methodName)) {

                            try {

                                Object invokedMethod = declaredDtoMethod.invoke(dtoObject);
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

