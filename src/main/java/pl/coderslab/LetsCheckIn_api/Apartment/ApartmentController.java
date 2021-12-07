package pl.coderslab.LetsCheckIn_api.Apartment;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.coderslab.LetsCheckIn_api.Country.CountryRepository;
import pl.coderslab.LetsCheckIn_api.Room.Room;
import pl.coderslab.LetsCheckIn_api.Room.RoomService;
import pl.coderslab.LetsCheckIn_api.Security.CurrentUser;
import pl.coderslab.LetsCheckIn_api.Utils.FileUploadUtil;

import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/apartment/")
@RequiredArgsConstructor
public class ApartmentController {

    private final ApartmentService apartmentService;
    private final RoomService roomService;
    private final CountryRepository countryRepository;


    @RequestMapping(value = "/listSearch", method = RequestMethod.GET)
    public String homePost(@RequestParam String country, @RequestParam String city, @RequestParam Long person, Model model) {
        model.addAttribute("person", person);
        List<Apartment> apartments = apartmentService.apartmentsFromSearch(country, city, person);
        model.addAttribute("apartments", apartmentService.apartmentsFromSearch(country, city, person));
        return "apartment/list";
    }

    @RequestMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("apartment", new Apartment());
        model.addAttribute("country",countryRepository.findAll());
        return "/apartment/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addPost(Apartment apartment,
//                          @RequestParam Integer roomAmount,
                          @RequestParam List<MultipartFile> pictures,
                          @AuthenticationPrincipal CurrentUser currentUser) throws IOException {
        apartment.setOwner(currentUser.getUser());
        apartmentService.saveApartment(apartment);
        if (pictures.get(0).getSize() != 0) {
            pictures.forEach(it -> {
                try {
                    apartmentService.addPictureAndSave(it, apartment);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        return "redirect:/apartment/edit/"+apartment.getId();
    }
    @RequestMapping("/edit/{apartmentId}")
    public String editForm(@PathVariable Long apartmentId, Model model) {
        model.addAttribute("apartment",apartmentService.getById(apartmentId));
        model.addAttribute("country",countryRepository.findAll());
        return "/apartment/edit";
    }


}
