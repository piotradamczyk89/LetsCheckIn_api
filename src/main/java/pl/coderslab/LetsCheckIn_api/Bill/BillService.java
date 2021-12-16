package pl.coderslab.LetsCheckIn_api.Bill;

import pl.coderslab.LetsCheckIn_api.Reservation.Reservation;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface BillService {
    Bill createFirstBill (Reservation reservation);
    Bill createNextBill (Bill paidBill);
    void saveBill(Bill bill);
    void delete (Bill bill);
    List<Bill> findByReservation(Reservation reservation);
    Bill getById (Long id);

}
