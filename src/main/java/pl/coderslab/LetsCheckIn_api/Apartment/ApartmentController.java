package pl.coderslab.LetsCheckIn_api.Apartment;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.coderslab.LetsCheckIn_api.Country.Country;
import pl.coderslab.LetsCheckIn_api.Country.CountryRepository;
import pl.coderslab.LetsCheckIn_api.Photo.PhotoService;
import pl.coderslab.LetsCheckIn_api.RentWay.RentWay;
import pl.coderslab.LetsCheckIn_api.RentWay.RentWayRepository;
import pl.coderslab.LetsCheckIn_api.Room.Room;
import pl.coderslab.LetsCheckIn_api.Room.RoomService;
import pl.coderslab.LetsCheckIn_api.SearchDto.SearchDto;
import pl.coderslab.LetsCheckIn_api.SearchDto.SearchLongDto;
import pl.coderslab.LetsCheckIn_api.Security.CurrentUser;
import pl.coderslab.LetsCheckIn_api.User.User;
import pl.coderslab.LetsCheckIn_api.Utils.FileUploadUtil;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@Controller
@RequestMapping("/apartment/")
@RequiredArgsConstructor
@SessionAttributes({"startDate", "endDate"})
public class ApartmentController {

    private final ApartmentService apartmentService;
    private final RoomService roomService;
    private final CountryRepository countryRepository;
    private final RentWayRepository rentWayRepository;
    private final PhotoService photoService;


    @RequestMapping(value = "/listSearch", method = RequestMethod.GET)
    public String searchList(@AuthenticationPrincipal CurrentUser currentUser, Model model, @Valid SearchDto searchDto, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("countries", countryRepository.findAll());
            model.addAttribute("searchLongDto",new SearchLongDto());
            return "/user/app";
        }
        if (currentUser != null) {
            model.addAttribute("user", currentUser.getUser());
        }
        model.addAttribute("startDate", searchDto.getStartDate());
        model.addAttribute("endDate", searchDto.getEndDate());
        model.addAttribute("searchDto", searchDto);
        model.addAttribute("apartments", apartmentService.apartmentsFromSearch(searchDto,currentUser));
        model.addAttribute("rooms", roomService.roomsFromSearch(searchDto,currentUser));
        return "apartment/list";
    }

    @RequestMapping(value = "/listLongSearch", method = RequestMethod.GET)
    public String searchLongList(@AuthenticationPrincipal CurrentUser currentUser, Model model, @Valid SearchLongDto searchLongDto, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("countries", countryRepository.findAll());
            model.addAttribute("searchDto",new SearchDto());
            return "/user/app";
        }
        searchLongDto.setEndDate();
        model.addAttribute("startDate", searchLongDto.getStartDate());
        model.addAttribute("endDate", searchLongDto.getEndDate());
        model.addAttribute("searchLongDto", searchLongDto);
        model.addAttribute("apartments",apartmentService.apartmentsLongFromSearch(searchLongDto,currentUser));

        return "apartment/list";
    }

    @RequestMapping("/list")
    public String list(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        model.addAttribute("apartments", apartmentService.findByOwner(currentUser.getUser()));
        return "apartment/list";
    }

    @RequestMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("apartment", new Apartment());
        model.addAttribute("country", countryRepository.findAll());
        model.addAttribute("rentWay", rentWayRepository.findAll());
        return "/apartment/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addPost(Apartment apartment,
                          @RequestParam List<MultipartFile> pictures,
                          @AuthenticationPrincipal CurrentUser currentUser) throws IOException {
        apartment.setOwner(currentUser.getUser());
        apartmentService.saveApartment(apartment);
        apartmentService.addPictureAndSave(pictures, apartment);
        return "redirect:/apartment/details/" + apartment.getId();
    }

    @RequestMapping("/edit/{apartmentId}")
    public String editForm(@PathVariable Long apartmentId, Model model) {
        model.addAttribute("apartment", apartmentService.getById(apartmentId));
        model.addAttribute("country", countryRepository.findAll());
        return "/apartment/edit";
    }

    @RequestMapping(value = "/edit/{apartmentId}", method = RequestMethod.POST)
    public String editPost(@PathVariable Long apartmentId, Apartment apartment) {
        apartmentService.saveApartment(apartment);
        return "redirect:/apartment/details/" + apartment.getId();
    }


    @RequestMapping("/details/{apartmentId}")
    public String details(@PathVariable Long apartmentId, Model model) {
        model.addAttribute("apartment", apartmentService.getById(apartmentId));
        return "/apartment/details";
    }

    @RequestMapping("/delete/{apartmentId}")
    public String delete(@PathVariable Long apartmentId, @AuthenticationPrincipal(errorOnInvalidType = false) CurrentUser currentUser) {
        Apartment apartment = apartmentService.getById(apartmentId);
        if (Objects.equals(apartment.getOwner().getId(), currentUser.getUser().getId())) {
            apartmentService.delete(apartment);
        }
        return "redirect:/apartment/list/";
    }

    @ModelAttribute("user")
    public User user(@AuthenticationPrincipal CurrentUser currentUser) {
        if(currentUser!=null) {
            return currentUser.getUser();
        }
        return null;
    }


}
