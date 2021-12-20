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

import java.util.Objects;


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
        if (Objects.equals(currentUser.getUser().getId(), bill.getReservation().getApartment().getOwner().getId())) {
            model.addAttribute("billEdit", bill);
            return "/bill/edit";
        }
        return "redirect:/user/app";
    }

    @RequestMapping(value = "/edit/{billId}", method = RequestMethod.POST)
    public String editPost(Bill bill, @PathVariable Long billId) {
        if(!bill.isPaid()) {
            return "redirect:/bill/list/" + bill.getReservation().getId();
        } // nie aktulane
        billService.saveBill(bill);
        if (bill.isPaid()) { // przerzucić do serwisu ? warunek nie aktulany
            Bill nextBill = billService.createNextBill(bill);
            if(nextBill!=null) {
             billService.saveBill(nextBill);
            }
        }
        return "redirect:/bill/list/" + bill.getReservation().getId();
    }

    @RequestMapping(value = "/add/{reservationId}")
    public String add (@PathVariable Long reservationId, Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        Reservation reservation = reservationService.getById(reservationId);
        if (reservation.getApartment().getOwner().getId()!=currentUser.getUser().getId()) {
            return "redirect:/user/app";
        }
        model.addAttribute("bill",new Bill());
        model.addAttribute("reservation",reservation);
        return "/bill/add";
    }

    @RequestMapping(value = "/add/{reservationId}", method = RequestMethod.POST)
    public String add (@PathVariable Long reservationId, Bill bill) {
        billService.saveBill(bill);
        return "redirect:/bill/list/"+reservationId;
    }

    @RequestMapping("/delete/{billId}")
    public String delete (@AuthenticationPrincipal CurrentUser currentUser, @PathVariable Long billId) {
        Bill bill = billService.getById(billId);
        if (Objects.equals(bill.getReservation().getApartment().getOwner().getId(), currentUser.getUser().getId())) {
            billService.delete(bill);
            return "redirect:/bill/list/"+bill.getReservation().getId();
        }
        return "redirect:/user/app";
    }

    @ModelAttribute("user")
    public User user(@AuthenticationPrincipal CurrentUser currentUser) {
        return currentUser.getUser();
    }

}
