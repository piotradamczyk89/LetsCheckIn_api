package pl.coderslab.LetsCheckIn_api.Reservation;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.coderslab.LetsCheckIn_api.Apartment.Apartment;
import pl.coderslab.LetsCheckIn_api.Apartment.ApartmentService;
import pl.coderslab.LetsCheckIn_api.Bill.Bill;
import pl.coderslab.LetsCheckIn_api.Bill.BillRepository;
import pl.coderslab.LetsCheckIn_api.Bill.BillService;
import pl.coderslab.LetsCheckIn_api.Room.Room;
import pl.coderslab.LetsCheckIn_api.Room.RoomService;
import pl.coderslab.LetsCheckIn_api.Security.CurrentUser;
import pl.coderslab.LetsCheckIn_api.User.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reservation/")
public class ReservationController {

    private final ReservationService reservationService;
    private final ApartmentService apartmentService;
    private final BillService billService;
    private final RoomService roomService;

    @RequestMapping("/addApart/{apartmentId}")
    public String addApart(Model model, @PathVariable Long apartmentId) {
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("apartment", apartmentService.getById(apartmentId));
        return "/reservation/add";
    }

    @RequestMapping(value = "/addApart/{apartmentId}", method = RequestMethod.POST)
    public String addApartPost(Reservation reservation, HttpSession session, @PathVariable Long apartmentId, Model model) {

        Apartment apartment = apartmentService.getById(apartmentId);
        reservation.setStartDate((LocalDate) session.getAttribute("startDate"));
        reservation.setEndDate((LocalDate) session.getAttribute("endDate"));
        reservation.setApartment(apartment);
        reservation.setName(apartmentId + "/0/" + reservation.getTenant().getId() + "/" + reservation.getStartDate());
        reservationService.saveReservation(reservation);
        billService.saveBill(billService.createFirstBill(reservation));
        model.addAttribute("reservation", reservation);
        return "redirect:/bill/list/" + reservation.getId();
    }

    @RequestMapping("/addRoom/{roomId}")
    public String addRoom(Model model, @PathVariable Long roomId) {
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("room", roomService.getById(roomId));
        return "/reservation/add";
    }

    @RequestMapping(value = "/addRoom/{roomId}", method = RequestMethod.POST)
    public String aadRoomPost(Reservation reservation, HttpSession session, @PathVariable Long roomId, Model model) {
        Room room = roomService.getById(roomId);
        reservation.setStartDate((LocalDate) session.getAttribute("startDate"));
        reservation.setEndDate((LocalDate) session.getAttribute("endDate"));
        reservation.setApartment(room.getApartment());
        reservation.setRoom(room);
        reservation.setName(room.getApartment().getId() + "/" + roomId + "/" + reservation.getTenant().getId() + "/" + reservation.getStartDate());
        reservationService.saveReservation(reservation);
        billService.saveBill(billService.createFirstBill(reservation));
        model.addAttribute("reservation", reservation);
        return "redirect:/bill/list/" + reservation.getId();
    }

    @RequestMapping("/list/actual/{apartmentId}")
    public String listApartActualReser(Model model, @PathVariable Long apartmentId, @AuthenticationPrincipal CurrentUser currentUser) {
        List<Reservation> reservations = reservationService.allOwnerActualApartmentReservation(currentUser, apartmentService.getById(apartmentId));
        model.addAttribute("reservations", reservations);
        return "/reservation/list";
    }

    @RequestMapping("/list/old/{apartmentId}")
    public String listApartOldReser(Model model, @PathVariable Long apartmentId, @AuthenticationPrincipal CurrentUser currentUser) {
        Slice<Reservation> reservations = reservationService.oldApartmentReservation(currentUser, apartmentId, 0, 5);
        model.addAttribute("reservations", reservations.toList());
        return "/reservation/list";
    }

    @RequestMapping("/list/actual")
    public String listActual(Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        model.addAttribute("reservations",
                reservationService.findByEndDateAfterAndTenant(LocalDate.now(), currentUser.getUser()));
        return "/reservation/list";
    }

    @RequestMapping("/list/history")
    public String listHistory(Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        model.addAttribute("reservations",
                reservationService.findByEndDateBeforeAndTenant(LocalDate.now(), currentUser.getUser()));
        return "/reservation/list";
    }

    @RequestMapping("/list/owner/afterDeadline/")
    public String listOwnerAfterDeadline(Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        model.addAttribute("reservations",
                reservationService.notPaidAfterDeadline(currentUser, 0, 10).toList());
        return "/reservation/list";
    }

    @RequestMapping("/delete/{reservationId}")
    public String delete(@AuthenticationPrincipal CurrentUser currentUser, @PathVariable Long reservationId) {
        Reservation reservation = reservationService.getById(reservationId);
        if (Objects.equals(reservation.getTenant().getId(), currentUser.getUser().getId())) {
            reservationService.delete(reservation);
            return "redirect:/bill/list/" + reservationId;
        }
        return "redirect:/user/app";
    }

    @RequestMapping("/checkAccount")
    public String checkAccount(@AuthenticationPrincipal CurrentUser currentUser, HttpServletRequest request, HttpServletResponse response) {
        reservationService.checkAccount(currentUser,request,response);
        return "redirect:/user/app";
    }


    @ModelAttribute("user")
    public User user(@AuthenticationPrincipal CurrentUser currentUser) {
        return currentUser.getUser();
    }

}
