package pl.coderslab.LetsCheckIn_api.Bill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.LetsCheckIn_api.Reservation.Reservation;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository <Bill, Long> {

    List<Bill> findByReservation(Reservation reservation);

}
