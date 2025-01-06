package com.product.infrastructure.adapter.util.validator;

import com.product.infrastructure.adapter.output.repository.ProductRepository;
import com.product.infrastructure.adapter.util.SkuConstrain;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SkuValidator implements ConstraintValidator<SkuConstrain, String> {


    private final ProductRepository productRepository;

    public SkuValidator(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return productRepository.findBySku(s).isEmpty();
    }
}
