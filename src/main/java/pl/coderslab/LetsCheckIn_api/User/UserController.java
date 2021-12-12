package pl.coderslab.LetsCheckIn_api.User;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("/register")
    public String createUser(Model model) {
        model.addAttribute("user",new User());
        return "/user/register";
    }
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String createUser(User user) {
        userService.saveUser(user);
        return "/user/main";
    }

    @RequestMapping(value = "/app")
    public String userMain (User user) {
        return "/user/main";
    }

}
