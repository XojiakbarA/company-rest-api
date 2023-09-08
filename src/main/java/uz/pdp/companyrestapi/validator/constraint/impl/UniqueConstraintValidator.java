package uz.pdp.companyrestapi.validator.constraint.impl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import uz.pdp.companyrestapi.validator.constraint.UniqueCorpName;
import uz.pdp.companyrestapi.service.CompanyService;

public class UniqueConstraintValidator implements ConstraintValidator<UniqueCorpName, String> {
    @Autowired
    private CompanyService companyService;

    @Override
    public void initialize(UniqueCorpName constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String corpName, ConstraintValidatorContext constraintValidatorContext) {
//        return !companyService.existsByCorpName(corpName);
        return true;
    }
}
