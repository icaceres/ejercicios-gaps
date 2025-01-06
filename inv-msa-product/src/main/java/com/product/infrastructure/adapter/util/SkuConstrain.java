package com.product.infrastructure.adapter.util;


import com.product.infrastructure.adapter.util.validator.SkuValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = SkuValidator.class)
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface SkuConstrain {
    String message() default "Sku ya existe.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
