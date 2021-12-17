package pl.coderslab.LetsCheckIn_api.Walidator;

import pl.coderslab.LetsCheckIn_api.SearchDto.SearchDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class CheckDatesValidator implements ConstraintValidator<CheckDates, SearchDto> {


    @Override
    public boolean isValid(SearchDto searchDto, ConstraintValidatorContext constraintValidatorContext) {
        return searchDto.getStartDate()!=null && searchDto.getEndDate()!=null && (searchDto.getStartDate().isAfter(LocalDate.now()) || searchDto.getStartDate().isEqual(LocalDate.now()))
                && searchDto.getEndDate().isAfter(searchDto.getStartDate());
    }

    @Override
    public void initialize(CheckDates constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }


}
