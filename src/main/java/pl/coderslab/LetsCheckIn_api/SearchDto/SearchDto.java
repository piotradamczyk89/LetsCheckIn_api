package pl.coderslab.LetsCheckIn_api.SearchDto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import pl.coderslab.LetsCheckIn_api.Walidator.CheckDates;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.time.LocalDate;


@Getter
@Setter
@CheckDates
public class SearchDto {

    private String country;
    @NotNull
    @NotEmpty
    private String city;
    @Min(1)
    @NotNull
    private Integer person;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent
    @NotNull
    private LocalDate startDate;
    @Future
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

}
