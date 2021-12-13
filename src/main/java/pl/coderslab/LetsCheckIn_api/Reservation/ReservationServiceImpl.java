package pl.coderslab.LetsCheckIn_api.Reservation;


import org.springframework.stereotype.Service;
import pl.coderslab.LetsCheckIn_api.Apartment.Apartment;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService{

    private ReservationRepository reservationRepository;

    @Override
    public void saveReservation(Reservation reservation) {
        reservationRepository.save(reservation);

    }

    @Override
    public void delete(Reservation reservation) {
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
}
