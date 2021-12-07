package pl.coderslab.LetsCheckIn_api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.LetsCheckIn_api.Apartment.ApartmentService;
import pl.coderslab.LetsCheckIn_api.User.User;
import pl.coderslab.LetsCheckIn_api.User.UserService;


@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;
    private final ApartmentService apartmentService;

    @RequestMapping("/")
    public String home(Model model) {
        return "home";
    }



    @PostMapping("/about")
    @ResponseBody
    public String about() { return "Here you can find some details for logged users"; }

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login() {
        return "admin/login";
    }

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
