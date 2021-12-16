package pl.coderslab.LetsCheckIn_api.Reservation;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.LetsCheckIn_api.Apartment.Apartment;
import pl.coderslab.LetsCheckIn_api.Bill.BillService;
import pl.coderslab.LetsCheckIn_api.Room.Room;
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
    public void delete(Reservation reservation) {
        billService.findByReservation(reservation).forEach(it->billService.delete(it));
        reservationRepository.delete(reservation);
    }

    @Override
    public Reservation getById(Long reservationId) {
        return reservationRepository.getById(reservationId);
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
    public List<Reservation> checkReservation(Long apartmentId, LocalDate startDate, LocalDate endDate) {
        return reservationRepository.checkReservation(apartmentId,startDate,endDate);
    }

    @Override
    public List<Reservation> apartmentReservationWithoutConfirm(Apartment apartment, User tenant) {
        return reservationRepository.apartmentReservationWithoutConfirm(apartment,tenant);
    }

    @Override
    public List<Reservation> allOwnerApartmentReservation (User owner, Apartment apartment) {
        return reservationRepository.allOwnerApartmentReservation(owner, apartment);
    }

    @Override
    public List<Reservation> findByEndDateBeforeAndTenant(LocalDate actualDate, User tenant) {
        return reservationRepository.findByEndDateBeforeAndTenant(actualDate,tenant);
    }

    @Override
    public List<Reservation> findByEndDateAfterAndTenant(LocalDate actualDate, User tenant) {
        return reservationRepository.findByEndDateAfterAndTenant(actualDate,tenant);
    }
}
