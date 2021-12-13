package pl.coderslab.LetsCheckIn_api.Reservation;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reservation/")
public class ReservationController {

    @RequestMapping("/add")
    public String add(Model model) {
        model.addAttribute("reservation", new Reservation());
        return "/reservation/add";
    }

}
