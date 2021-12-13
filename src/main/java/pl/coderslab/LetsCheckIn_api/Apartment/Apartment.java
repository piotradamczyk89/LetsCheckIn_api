package pl.coderslab.LetsCheckIn_api.Apartment;


import lombok.Getter;
import lombok.Setter;
import pl.coderslab.LetsCheckIn_api.Country.Country;
import pl.coderslab.LetsCheckIn_api.Photo.Photo;
import pl.coderslab.LetsCheckIn_api.RentWay.RentWay;
import pl.coderslab.LetsCheckIn_api.Reservation.Reservation;
import pl.coderslab.LetsCheckIn_api.Room.Room;
import pl.coderslab.LetsCheckIn_api.User.User;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User owner; // do przegadania

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "apartment")
    private List<Room> rooms;

    @OneToMany(mappedBy = "apartment")
    private List<Photo> photos;
    @OneToMany(mappedBy = "apartment")
    private List<Reservation> reservations;

    @NotNull
    @NotBlank
    private String name;

    @ManyToOne
    private RentWay rentWay;

    @ManyToOne
    private Country country;
    @NotNull
    @NotBlank
    private String city;
    private String description;

    @Min(value = 1)
    private Integer toilets;
    @Min(value = 1)
    private Double area;
    @DecimalMin(value = "0")
    private BigDecimal apartmentPrice;

    @NotNull
    @NotBlank
    private String address;



}
