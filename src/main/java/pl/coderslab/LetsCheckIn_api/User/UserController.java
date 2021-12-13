package pl.coderslab.LetsCheckIn_api.User;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.coderslab.LetsCheckIn_api.Apartment.ApartmentService;
import pl.coderslab.LetsCheckIn_api.Country.Country;
import pl.coderslab.LetsCheckIn_api.Country.CountryRepository;
import pl.coderslab.LetsCheckIn_api.DtoApartmentAndRoom.DtoApartmentAndRoom;
import pl.coderslab.LetsCheckIn_api.Security.CurrentUser;


@Controller
@RequestMapping("/user/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ApartmentService apartmentService;
    private final CountryRepository countryRepository;


    @GetMapping("/register")
    public String createUser(Model model) {
        model.addAttribute("user",new User());
        return "/user/register";
    }
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String createUser(User user) {
        userService.saveUser(user);
        return "redirect:/user/login";
    }

    @RequestMapping(value = "/app")
    public String userMain (Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        model.addAttribute("user",currentUser.getUser());
        model.addAttribute("countries",countryRepository.findAll());
        model.addAttribute("country",new Country());
      //  model.addAttribute("dto",new DtoApartmentAndRoom());
        return "/user/app";
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login() {
        return "user/login";
    }



//    @ModelAttribute("user")
//    public User user (@AuthenticationPrincipal CurrentUser currentUser) {
//        return currentUser.getUser();
//    }





}
