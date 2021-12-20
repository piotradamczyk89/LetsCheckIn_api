package pl.coderslab.LetsCheckIn_api.User;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.LetsCheckIn_api.Apartment.ApartmentService;
import pl.coderslab.LetsCheckIn_api.Country.CountryRepository;
import pl.coderslab.LetsCheckIn_api.HttpClient.HttpClient;
import pl.coderslab.LetsCheckIn_api.Nordlinger.Account;
import pl.coderslab.LetsCheckIn_api.Nordlinger.Bank;
import pl.coderslab.LetsCheckIn_api.Nordlinger.BankConnection;
import pl.coderslab.LetsCheckIn_api.Nordlinger.Token;
import pl.coderslab.LetsCheckIn_api.SearchDto.SearchDto;
import pl.coderslab.LetsCheckIn_api.SearchDto.SearchLongDto;
import pl.coderslab.LetsCheckIn_api.Security.CurrentUser;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpRequest;
import java.util.List;


@Controller
@RequestMapping("/user/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ApartmentService apartmentService;
    private final CountryRepository countryRepository;
    private final HttpClient httpClient;



    @GetMapping("/register")
    public String createUser(Model model) {
        model.addAttribute("userNew", new User());
        return "/user/register";
    }
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String createUser(@ModelAttribute("userNew") User user) {
        userService.saveUser(user);
        return "redirect:/user/login";
    }
    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login() {
        return "user/login";
    }

    @RequestMapping(value = "/app")
    public String userMain(Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        model.addAttribute("user", currentUser.getUser());
        model.addAttribute("countries", countryRepository.findAll());
        model.addAttribute("searchDto", new SearchDto());
        model.addAttribute("searchLongDto", new SearchLongDto());
        return "/user/app";
    }

    @RequestMapping("/account/add")
    public String accountAdd () {
        return "/user/accountKey";
    }

    @RequestMapping(value = "/account/add",method = RequestMethod.POST)
    public String accountAddPost (HttpServletResponse response, @RequestParam String secretId, @RequestParam String secretKey, @AuthenticationPrincipal CurrentUser currentUser) {
        Token token = httpClient.requestToken(secretKey, secretId);
        User user = currentUser.getUser();
        user.setToken(token.getAccess());
        user.setRefreshToken(token.getRefresh());
        userService.saveUser(user);
        Cookie cookie = new Cookie("token",user.getUserName());
        cookie.setMaxAge(86399);
        response.addCookie(cookie);
        return "redirect:/user/account/choseBank";
    }

    @RequestMapping("/account/choseBank")
    public String choseBank (@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        User user = currentUser.getUser();
        model.addAttribute("banks", httpClient.requestBanks(user.getToken()));
        model.addAttribute("bank",new Bank());
        return "/user/banks";
    }

    @RequestMapping(value = "/account/choseBank", method = RequestMethod.POST)
    public String choseBank (@AuthenticationPrincipal CurrentUser currentUser, Bank bank) {
     //   BankConnection bankConnection = httpClient.connectToBank(bank.getId(), currentUser.getUser().getToken());
        BankConnection bankConnection = httpClient.connectToBank("SANDBOXFINANCE_SFIN0000", currentUser.getUser().getToken());
        return "redirect:"+bankConnection.getLink();
    }

    @RequestMapping("/account/accounts")
    public String connectAccount (@AuthenticationPrincipal CurrentUser currentUser, @RequestParam String ref, Model model) {
        User user = currentUser.getUser();
        Account account = httpClient.requestAccounts(user.getToken(), ref);
        List<String> accounts = account.getAccounts();
        if (accounts!=null) {
            user.setAccountId(accounts.get(0));
            userService.saveUser(user);
            model.addAttribute("status","ok");
        } else {
            model.addAttribute("status","nok");
        }
        return "/user/accountFinish";
    }

//    @RequestMapping("/account/refreshToken")
//    public String refreshToken (@AuthenticationPrincipal CurrentUser currentUser) {
//        User user = currentUser.getUser();
//        Token token = httpClient.requestRefreshToken(user.getRefreshToken());
//        user.setToken(token.getAccess());
//        userService.saveUser(user);
//        return "redirect"
//    }


    @ModelAttribute("user")
    public User user(@AuthenticationPrincipal CurrentUser currentUser) {
        if(currentUser!=null) {
            return currentUser.getUser();
        }
        return null;
    }


}
