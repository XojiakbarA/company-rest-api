package uz.pdp.companyrestapi.validator.constraint;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import uz.pdp.companyrestapi.validator.constraint.impl.UniqueConstraintValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueConstraintValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueCorpName {
    String message() default "A company with the given corpName already exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
