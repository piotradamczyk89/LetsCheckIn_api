package pl.coderslab.LetsCheckIn_api.Paid;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.LetsCheckIn_api.Reservation.Reservation;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Paid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Reservation reservation;


}
