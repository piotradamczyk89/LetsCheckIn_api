package pl.coderslab.LetsCheckIn_api.DtoApartmentAndRoom;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.LetsCheckIn_api.Country.Country;

import java.time.LocalDate;


@Getter
@Setter
public class DtoApartmentAndRoom {
    private Integer persons;
    private String city;
    private Country country;
    private LocalDate startDate;
    private LocalDate endDate;

}
