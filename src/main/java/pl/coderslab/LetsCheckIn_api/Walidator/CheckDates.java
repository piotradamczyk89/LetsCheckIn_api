package pl.coderslab.LetsCheckIn_api.Walidator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CheckDatesValidator.class)
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckDates {

    String message() default "Data początku musi być przed data konca i nie moze być przeszła";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
