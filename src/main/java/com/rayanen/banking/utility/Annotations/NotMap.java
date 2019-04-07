package com.rayanen.banking.utility.Annotations;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface NotMap {
    Class value() ;
}
