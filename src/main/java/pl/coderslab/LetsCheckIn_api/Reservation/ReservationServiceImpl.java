package pl.coderslab.LetsCheckIn_api.Reservation;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;
import pl.coderslab.LetsCheckIn_api.Apartment.Apartment;
import pl.coderslab.LetsCheckIn_api.Bill.Bill;
import pl.coderslab.LetsCheckIn_api.Bill.BillService;
import pl.coderslab.LetsCheckIn_api.HttpClient.HttpClient;
import pl.coderslab.LetsCheckIn_api.Nordlinger.Token;
import pl.coderslab.LetsCheckIn_api.Nordlinger.Transactions.Transactions;
import pl.coderslab.LetsCheckIn_api.Room.Room;
import pl.coderslab.LetsCheckIn_api.Security.CurrentUser;
import pl.coderslab.LetsCheckIn_api.User.User;
import pl.coderslab.LetsCheckIn_api.User.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final BillService billService;
    private final HttpClient httpClient;
    private final UserService userService;

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
        billService.findByReservation(reservation).forEach(it -> billService.delete(it));
        reservationRepository.delete(reservation);
    }

    @Override
    public Reservation getById(Long reservationId) {
        return reservationRepository.getById(reservationId);
    }


    @Override
    public List<Reservation> checkReservation(Long apartmentId, LocalDate startDate, LocalDate endDate) {
        return reservationRepository.checkReservation(apartmentId, startDate, endDate);
    }


    @Override
    public List<Reservation> allOwnerActualApartmentReservation(CurrentUser currentUser, Apartment apartment) {
        return reservationRepository.allOwnerActualApartmentReservation(currentUser.getUser(), apartment);
    }

    @Override
    public List<Reservation> findByEndDateBeforeAndTenant(LocalDate actualDate, User tenant) {
        return reservationRepository.findByEndDateBeforeAndTenant(actualDate, tenant);
    }

    @Override
    public List<Reservation> findByEndDateAfterAndTenant(LocalDate actualDate, User tenant) {
        return reservationRepository.findByEndDateAfterAndTenant(actualDate, tenant);
    }

    @Override
    public Slice<Reservation> notPaidAfterDeadline(CurrentUser currentUser, Integer pageNumber, Integer amount) {
        return reservationRepository.notPaidAfterDeadline(currentUser.getUser().getId(), PageRequest.of(pageNumber, amount));
    }

    @Override
    public Slice<Reservation> notPaidApartmentBeforeDeadline(CurrentUser currentUser, Long apartmentId, Integer pageNumber, Integer amount) {
        return reservationRepository.notPaidBeforeDeadline(currentUser.getUser().getId(), apartmentId, PageRequest.of(pageNumber, amount));
    }

    @Override
    public Slice<Reservation> oldApartmentReservation(CurrentUser currentUser, Long apartmentId, Integer pageNumber, Integer amount) {
        return reservationRepository.oldApartmentReservation(currentUser.getUser().getId(), apartmentId, PageRequest.of(pageNumber, amount));
    }

    @Override
    public List<Reservation> notPaidReservation(CurrentUser currentUser) {
        return reservationRepository.notPaidReservation(currentUser.getUser().getId());
    }

    @Override
    public void checkAccount(CurrentUser currentUser, HttpServletRequest request, HttpServletResponse response) {
        User user = currentUser.getUser();
        if (WebUtils.getCookie(request, "token") == null) {
            Token token = httpClient.requestRefreshToken(user.getRefreshToken());
            user.setToken(token.getAccess());
            userService.saveUser(user);
            Cookie cookie = new Cookie("token", user.getUserName());
            cookie.setMaxAge(86399);
            response.addCookie(cookie);
        }
        Transactions transactions = httpClient.requestTransactions(user.getAccountId(), user.getToken());
        System.out.println(transactions);
        List<Reservation> reservations = notPaidReservation(currentUser);
        reservations.forEach(reservation -> {
            List<BigDecimal> suma = new ArrayList<>();
            transactions.getTransactions().getBooked().forEach(booked -> {
                if (booked.getRemittanceInformationUnstructured().equals(reservation.getName())) {
                    suma.add(booked.getTransactionAmount().getAmount());
                }
            });
            reservation.getBills().forEach(bill -> {
                do {
                    if (bill.isPaid()) {
                        suma.add(bill.getCost().negate());
                        break;
                    } else {
                        if (suma.stream().reduce(BigDecimal.ZERO, BigDecimal::add).compareTo(bill.getCost()) >= 0) {
                            bill.setPaid(true);
                            billService.saveBill(bill);
                            suma.add(bill.getCost().negate());
                            Bill nextBill = billService.createNextBill(bill);
                            if (nextBill != null) {
                                billService.saveBill(nextBill);
                                bill = nextBill;
                            }
                        } else {
                            break;
                        }
                    }
                } while (true);
            });
        });
    }
}
