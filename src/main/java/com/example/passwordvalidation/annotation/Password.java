package com.example.passwordvalidation.annotation;


import com.example.passwordvalidation.validator.PasswordValidator;
import org.hibernate.validator.constraints.Length;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.Retention;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {PasswordValidator.class})
@NotEmpty(message="{pwd.isEmpty}")
@Length(min = 6,max = 12, message = "{pwd.length}")
@Pattern(regexp="(^(?=.*\\d)(?=.*[a-z]).{5,12})",message = "{pwd.oneEach}")
@Pattern(regexp = "[\\da-z]{6,12}",message = "{pwd.DigitAndLetter}")
public @interface Password {
    String message () default "{pwd.Seq}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
