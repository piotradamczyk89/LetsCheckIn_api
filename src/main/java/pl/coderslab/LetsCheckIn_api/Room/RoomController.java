package pl.coderslab.LetsCheckIn_api.Room;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.coderslab.LetsCheckIn_api.Apartment.ApartmentService;

import java.util.List;
import java.util.Map;
import java.util.Set;


@Controller
@RequiredArgsConstructor
@RequestMapping("/room/")
public class RoomController {

    private final RoomService roomService;
    private final ApartmentService apartmentService;

    @RequestMapping ("/add/{apartment_id}")
    public String addForm (@PathVariable Long apartment_id, Model model) {
        model.addAttribute("apartment",apartmentService.getById(apartment_id));
        model.addAttribute("room",new Room());
        return "/room/add";
    }

    @RequestMapping (value = "/add/{apartment_id}", method = RequestMethod.POST)
    public String addPost (@PathVariable Long apartment_id,
                           Model model,
                           Room room,
                           Room room2,
                           @RequestParam Map<String,Room> map,
                           @RequestParam List<MultipartFile> pictures,
                           @PathVariable Integer roomAmount) {
        model.addAttribute("apartment",apartmentService.getById(apartment_id));
        Set<String> keys = map.keySet();
        for (String key : keys) {
            roomService.saveRoom(map.get(key));
        }

        return "edit";
    }



}
