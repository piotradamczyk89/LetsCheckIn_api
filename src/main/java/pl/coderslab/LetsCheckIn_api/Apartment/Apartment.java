package pl.coderslab.LetsCheckIn_api.Apartment;


import lombok.Getter;
import lombok.Setter;
import pl.coderslab.LetsCheckIn_api.Country.Country;
import pl.coderslab.LetsCheckIn_api.Photo.Photo;
import pl.coderslab.LetsCheckIn_api.RentWay.RentWay;
import pl.coderslab.LetsCheckIn_api.Room.Room;
import pl.coderslab.LetsCheckIn_api.User.User;

import javax.persistence.*;
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

    private String name;

    @ManyToOne
    private RentWay rentWay;

    @ManyToOne
    private Country country;

    private String city;
    private String description;

    private Integer toilets;
    private Double area;
    private BigDecimal apartmentPrice;

    private String address;
//    @Transient
//    private Integer roomAmount;


}
