package pl.coderslab.LetsCheckIn_api.Reservation;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import pl.coderslab.LetsCheckIn_api.Apartment.Apartment;
import pl.coderslab.LetsCheckIn_api.Bill.BillService;
import pl.coderslab.LetsCheckIn_api.Room.Room;
import pl.coderslab.LetsCheckIn_api.Security.CurrentUser;
import pl.coderslab.LetsCheckIn_api.User.User;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService{

    private final ReservationRepository reservationRepository;
    private final BillService billService;

    @Override
    public void saveReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> findByApartment(Apartment apartment) {
        return reservationRepository.findByApartment(apartment);
    }

    @Override
    public List<Reservation> findByRoom(Room room) {
        return reservationRepository.findByRoom(room);
    }

    @Override
    public void delete(Reservation reservation) {
        billService.findByReservation(reservation).forEach(it->billService.delete(it));
        reservationRepository.delete(reservation);
    }

    @Override
    public Reservation getById(Long reservationId) {
        return reservationRepository.getById(reservationId);
    }


    @Override
    public List<Reservation> checkReservation(Long apartmentId, LocalDate startDate, LocalDate endDate) {
        return reservationRepository.checkReservation(apartmentId,startDate,endDate);
    }



    @Override
    public List<Reservation> allOwnerActualApartmentReservation (CurrentUser currentUser, Apartment apartment) {
        return reservationRepository.allOwnerActualApartmentReservation(currentUser.getUser(), apartment);
    }

    @Override
    public List<Reservation> findByEndDateBeforeAndTenant(LocalDate actualDate, User tenant) {
        return reservationRepository.findByEndDateBeforeAndTenant(actualDate,tenant);
    }

    @Override
    public List<Reservation> findByEndDateAfterAndTenant(LocalDate actualDate, User tenant) {
        return reservationRepository.findByEndDateAfterAndTenant(actualDate,tenant);
    }

    @Override
    public Slice<Reservation> notPaidAfterDeadline(CurrentUser currentUser, Integer pageNumber, Integer amount) {
        return reservationRepository.notPaidAfterDeadline(currentUser.getUser().getId(), PageRequest.of(pageNumber,amount));
    }

    @Override
    public Slice<Reservation> notPaidApartmentBeforeDeadline(CurrentUser currentUser, Long apartmentId, Integer pageNumber, Integer amount) {
        return reservationRepository.notPaidBeforeDeadline(currentUser.getUser().getId(),apartmentId, PageRequest.of(pageNumber,amount));
    }

    @Override
    public Slice<Reservation> oldApartmentReservation(CurrentUser currentUser, Long apartmentId, Integer pageNumber, Integer amount) {
        return reservationRepository.oldApartmentReservation(currentUser.getUser().getId(), apartmentId, PageRequest.of(pageNumber,amount));
    }


}
