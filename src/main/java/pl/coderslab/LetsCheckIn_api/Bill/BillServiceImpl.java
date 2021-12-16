package pl.coderslab.LetsCheckIn_api.Bill;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.LetsCheckIn_api.Reservation.Reservation;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;

    @Override
    public Bill createFirstBill(Reservation reservation) {
        Bill bill = new Bill();
        bill.setReservation(reservation);
        bill.setCreateDate(LocalDate.now());
        bill.setExpireDate(LocalDate.now().plusDays(7));
        bill.setPaid(false);

        if (reservation.getApartment().getRentWay().getId() == 1) {
            bill.setName("Advance payment");
            bill.setCost(reservation.getApartment().getApartmentPrice()
                    .multiply(new BigDecimal(ChronoUnit.DAYS.between(reservation.getStartDate(), reservation.getEndDate())))
                    .multiply(new BigDecimal("0.25")));
        } else if (reservation.getApartment().getRentWay().getId() == 2) {
            bill.setName("Deposit");
            bill.setCost(reservation.getApartment().getApartmentPrice());
        } else if (reservation.getApartment().getRentWay().getId() == 3) {
            bill.setName("Advance payment");
            bill.setCost(reservation.getRoom().getRoomPrice()
                    .multiply(new BigDecimal(ChronoUnit.DAYS.between(reservation.getStartDate(), reservation.getEndDate())))
                    .multiply(new BigDecimal("0.25")));
        }

        return bill;
    }

    @Override
    public Bill createNextBill(Bill paidBill) {
        Bill bill = new Bill();
        bill.setReservation(paidBill.getReservation());
        bill.setCreateDate(LocalDate.now());
        bill.setPaid(false);
        if (paidBill.getReservation().getApartment().getRentWay().getId() == 1 && paidBill.getName().equals("Advance payment")) {
            bill.setName("Full payment");
            bill.setExpireDate(paidBill.getReservation().getStartDate().plusDays(1));
            bill.setCost(paidBill.getReservation().getApartment().getApartmentPrice()
                    .multiply(new BigDecimal(ChronoUnit.DAYS.between(paidBill.getReservation().getStartDate(), paidBill.getReservation().getEndDate())))
                    .subtract(paidBill.getCost()));
        } else if (paidBill.getReservation().getApartment().getRentWay().getId() == 2
                && (paidBill.getName().equals("Month pay") || paidBill.getName().equals("Deposit"))) {
            bill.setName("Month pay");
            bill.setCost(paidBill.getReservation().getApartment().getApartmentPrice());
            if (paidBill.getName().equals("Deposit")) {
                bill.setExpireDate(paidBill.getReservation().getStartDate().plusMonths(1));
            } else if (paidBill.getReservation().getEndDate().isAfter(paidBill.getExpireDate().plusMonths(1))) {
                bill.setExpireDate(paidBill.getExpireDate().plusMonths(1));
            } else {
                return null;
            }
        } else if (paidBill.getReservation().getApartment().getRentWay().getId() == 3 && paidBill.getName().equals("Advance payment")) {
            bill.setName("Full payment");
            bill.setExpireDate(paidBill.getReservation().getStartDate().plusDays(1));
            bill.setCost(paidBill.getReservation().getRoom().getRoomPrice()
                    .multiply(new BigDecimal(ChronoUnit.DAYS.between(paidBill.getReservation().getStartDate(), paidBill.getReservation().getEndDate())))
                    .subtract(paidBill.getCost()));
        }
        return bill;
    }

    @Override
    public void saveBill(Bill bill) {
        billRepository.save(bill);
    }

    @Override
    public void delete(Bill bill) {
        billRepository.delete(bill);
    }

    @Override
    public List<Bill> findByReservation(Reservation reservation) {
        return billRepository.findByReservation(reservation);
    }

    @Override
    public Bill getById(Long id) {
        return billRepository.getById(id);
    }
}
