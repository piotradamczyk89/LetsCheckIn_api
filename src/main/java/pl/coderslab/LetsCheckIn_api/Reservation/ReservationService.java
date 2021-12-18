package pl.coderslab.LetsCheckIn_api.Reservation;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.multipart.MultipartFile;
import pl.coderslab.LetsCheckIn_api.Apartment.Apartment;
import pl.coderslab.LetsCheckIn_api.Room.Room;
import pl.coderslab.LetsCheckIn_api.Security.CurrentUser;
import pl.coderslab.LetsCheckIn_api.User.User;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {
    void saveReservation (Reservation reservation);
    void delete (Reservation reservation);
    Reservation getById (Long reservationId);
    List<Reservation>findByApartment (Apartment apartment);
    List<Reservation> findByRoom(Room room);
    List<Reservation> checkReservation (Long apartmentId, LocalDate startDate, LocalDate endDate);
    List<Reservation> allOwnerActualApartmentReservation (CurrentUser currentUser, Apartment apartment);

    List<Reservation> findByEndDateBeforeAndTenant(LocalDate actualDate, User tenant);

    List<Reservation> findByEndDateAfterAndTenant(LocalDate actualDate, User tenant);

    Slice<Reservation> notPaidAfterDeadline (CurrentUser currentUser, Integer pageNumber, Integer amount);

    Slice<Reservation> notPaidApartmentBeforeDeadline (CurrentUser currentUser, Long apartmentId, Integer pageNumber, Integer amount);

    Slice<Reservation> oldApartmentReservation (CurrentUser currentUser, Long apartmentId, Integer pageNumber, Integer amount);

}
