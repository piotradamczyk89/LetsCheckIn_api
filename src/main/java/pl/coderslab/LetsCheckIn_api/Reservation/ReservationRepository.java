package pl.coderslab.LetsCheckIn_api.Reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.LetsCheckIn_api.Apartment.Apartment;

import java.util.List;


@Repository
public interface ReservationRepository extends JpaRepository <Reservation,Long>{

    List<Reservation> findByApartment (Apartment apartment);


}
