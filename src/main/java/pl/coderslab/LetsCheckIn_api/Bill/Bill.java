package pl.coderslab.LetsCheckIn_api.Bill;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.LetsCheckIn_api.Reservation.Reservation;
import pl.coderslab.LetsCheckIn_api.Status.Status;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @ManyToOne
    private Reservation reservation;

    private LocalDate createDate;
    private LocalDate expireDate;

    private boolean isPaid;

    private BigDecimal cost;



}
