package pl.coderslab.LetsCheckIn_api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.LetsCheckIn_api.Apartment.ApartmentService;
import pl.coderslab.LetsCheckIn_api.Country.Country;
import pl.coderslab.LetsCheckIn_api.Country.CountryRepository;
import pl.coderslab.LetsCheckIn_api.User.User;
import pl.coderslab.LetsCheckIn_api.User.UserService;


@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;
    private final ApartmentService apartmentService;
    private final CountryRepository countryRepository;

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("countries",countryRepository.findAll());
        model.addAttribute("country",new Country());
        return "home";
    }


//    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
//    public String login() {
//        return "user/login";
//    }

    @GetMapping("/create-user")
    @ResponseBody
    public String createUser() {
        User user = new User();
        user.setUserName("admin");
        user.setPassword("admin");
        userService.saveUser(user);
        return "admin";
    }

}
