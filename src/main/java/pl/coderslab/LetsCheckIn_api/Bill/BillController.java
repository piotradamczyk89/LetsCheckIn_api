package pl.coderslab.LetsCheckIn_api.Bill;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.LetsCheckIn_api.Reservation.Reservation;
import pl.coderslab.LetsCheckIn_api.Reservation.ReservationService;
import pl.coderslab.LetsCheckIn_api.Security.CurrentUser;
import pl.coderslab.LetsCheckIn_api.User.User;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/bill/")
@RequiredArgsConstructor
@SessionAttributes("isPaid")
public class BillController {


    private final BillService billService;
    private final ReservationService reservationService;

    @RequestMapping("/list/{reservationId}")
    public String edit(Model model, @PathVariable Long reservationId, @AuthenticationPrincipal CurrentUser currentUser) {
        Reservation reservation = reservationService.getById(reservationId);
        if (reservation.getTenant().getId() == currentUser.getUser().getId() || reservation.getApartment().getOwner().getId() == currentUser.getUser().getId()) {
            model.addAttribute("bills", billService.findByReservation(reservation));
            return "/bill/list";
        } else {
            return "redirect:/user/app";
        }
    }

    @RequestMapping("/edit/{billId}")
    public String edit(@AuthenticationPrincipal CurrentUser currentUser, Model model, @PathVariable Long billId) {
        Bill bill = billService.getById(billId);
        if (currentUser.getUser().getId() == bill.getReservation().getApartment().getOwner().getId()) {
            model.addAttribute("billEdit", bill);
            return "/bill/edit";
        }
        return "redirect:/user/app";
    }

    @RequestMapping(value = "/edit/{billId}", method = RequestMethod.POST)
    public String editPost(Bill bill, @PathVariable Long billId) {
        billService.saveBill(bill);
        if (bill.isPaid()) {
            Bill nextBill = billService.createNextBill(bill);
            if(nextBill!=null) {
             billService.saveBill(nextBill);
            }
        }
        return "redirect:/bill/list/" + bill.getReservation().getId();
    }

    @ModelAttribute("user")
    public User user(@AuthenticationPrincipal CurrentUser currentUser) {
        return currentUser.getUser();
    }

}
