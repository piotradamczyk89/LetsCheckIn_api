package pl.coderslab.LetsCheckIn_api.Reservation;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.LetsCheckIn_api.Apartment.Apartment;
import pl.coderslab.LetsCheckIn_api.Paid.Paid;
import pl.coderslab.LetsCheckIn_api.Room.Room;
import pl.coderslab.LetsCheckIn_api.User.User;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Getter
@Setter
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Apartment apartment;
    @ManyToOne
    private Room room;
    @ManyToOne
    private User user;

    private LocalDate startDate;
    private LocalDate endDate;




}
