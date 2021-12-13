package pl.coderslab.LetsCheckIn_api.Reservation;

import org.springframework.web.multipart.MultipartFile;
import pl.coderslab.LetsCheckIn_api.Apartment.Apartment;
import pl.coderslab.LetsCheckIn_api.User.User;

import java.util.List;

public interface ReservationService {
    void saveReservation (Reservation reservation);
    void delete (Reservation reservation);
    Reservation getById (Long reservationId);
    List<Reservation> findByApartment (Apartment apartment);
}
