package pl.coderslab.LetsCheckIn_api.SearchDto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;


@Getter
@Setter
public class SearchLongDto {

    private String country;
    @NotNull
    @NotEmpty
    private String city;
    @Min(3)
    @NotNull
    private Integer month;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    @FutureOrPresent
    private LocalDate startDate;
    @NotNull
    @Min(value = 1)
    private Double area;
    private LocalDate endDate;

    public void setEndDate() {
        this.endDate = startDate.plusMonths(month);
    }
}
