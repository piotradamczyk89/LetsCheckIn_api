package pl.coderslab.LetsCheckIn_api.Reservation;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.LetsCheckIn_api.Apartment.Apartment;
import pl.coderslab.LetsCheckIn_api.Bill.Bill;
import pl.coderslab.LetsCheckIn_api.Room.Room;
import pl.coderslab.LetsCheckIn_api.User.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Entity
@Getter
@Setter
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @ManyToOne
    private Apartment apartment;
    @ManyToOne
    private Room room;
    @ManyToOne
    private User tenant;
    @OneToMany(mappedBy = "reservation")
    private List<Bill> bills;
    private LocalDate startDate;
    private LocalDate endDate;


}
