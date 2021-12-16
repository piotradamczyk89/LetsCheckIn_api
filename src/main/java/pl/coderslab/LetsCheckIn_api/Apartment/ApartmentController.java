package pl.coderslab.LetsCheckIn_api.Apartment;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.coderslab.LetsCheckIn_api.Country.Country;
import pl.coderslab.LetsCheckIn_api.Country.CountryRepository;
import pl.coderslab.LetsCheckIn_api.Photo.PhotoService;
import pl.coderslab.LetsCheckIn_api.RentWay.RentWay;
import pl.coderslab.LetsCheckIn_api.RentWay.RentWayRepository;
import pl.coderslab.LetsCheckIn_api.Room.Room;
import pl.coderslab.LetsCheckIn_api.Room.RoomService;
import pl.coderslab.LetsCheckIn_api.Security.CurrentUser;
import pl.coderslab.LetsCheckIn_api.Utils.FileUploadUtil;

import javax.websocket.server.PathParam;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public String homePost(@RequestParam (name="name") String country, @RequestParam String city, @RequestParam Integer person
    , @RequestParam String startDate, @RequestParam String endDate, @AuthenticationPrincipal CurrentUser currentUser, Model model) {
        LocalDate startDate_ = LocalDate.parse(startDate);
        LocalDate endDate_ = LocalDate.parse(endDate);
        model.addAttribute("country",country);
        model.addAttribute("city",city);
        model.addAttribute("persons",person);
        model.addAttribute("startDate",startDate_);
        model.addAttribute("endDate",endDate_);
        Long userId=-1l;
        if (currentUser!=null) {
            userId=currentUser.getUser().getId();
            model.addAttribute("user",currentUser.getUser());
        }
        model.addAttribute("apartments", apartmentService.apartmentsFromSearch(country,city,person.longValue(),startDate_,endDate_,userId));
        model.addAttribute("rooms", roomService.roomsFromSearch(country,city,person,startDate_,endDate_,userId));
        return "apartment/list";


    }
    @RequestMapping("/list")
    public String list (@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        model.addAttribute("apartments",apartmentService.findByOwner(currentUser.getUser()));
        if (currentUser!=null) {
            model.addAttribute("user",currentUser.getUser());
        }
        return "apartment/list";
    }

    @RequestMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("apartment", new Apartment());
        model.addAttribute("country",countryRepository.findAll());
        model.addAttribute("rentWay",rentWayRepository.findAll());
        return "/apartment/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addPost(Apartment apartment,
                          @RequestParam List<MultipartFile> pictures,
                          @AuthenticationPrincipal CurrentUser currentUser) throws IOException {
        apartment.setOwner(currentUser.getUser());
        apartmentService.saveApartment(apartment);
        apartmentService.addPictureAndSave(pictures,apartment);
        return "redirect:/apartment/details/"+apartment.getId();
    }
    @RequestMapping("/edit/{apartmentId}")
    public String editForm(@PathVariable Long apartmentId, Model model) {

        model.addAttribute("apartment",apartmentService.getById(apartmentId));
        model.addAttribute("country",countryRepository.findAll());
        return "/apartment/edit";
    }
    @RequestMapping(value = "/edit/{apartmentId}", method = RequestMethod.POST)
    public String editPost(@PathVariable Long apartmentId, Apartment apartment) {
        apartmentService.saveApartment(apartment);
        return "redirect:/apartment/details"+apartment.getId();
    }


    @RequestMapping("/details/{apartmentId}")
    public String details(@PathVariable Long apartmentId, Model model, @AuthenticationPrincipal (errorOnInvalidType = false) CurrentUser currentUser) {
        if (currentUser!=null) {
            model.addAttribute("userId", currentUser.getUser().getId());
        }
        model.addAttribute("apartment",apartmentService.getById(apartmentId));
        return "/apartment/details";
    }
    @RequestMapping("/delete/{apartmentId}")
    public String delete(@PathVariable Long apartmentId, @AuthenticationPrincipal (errorOnInvalidType = false) CurrentUser currentUser) {
        Apartment apartment = apartmentService.getById(apartmentId);
        if (apartment.getOwner().getId()==currentUser.getUser().getId()) {
           apartmentService.delete(apartment);
        }
        return "redirect:/apartment/list/";
    }




}
