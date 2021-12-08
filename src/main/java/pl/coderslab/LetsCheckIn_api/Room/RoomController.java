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
import pl.coderslab.LetsCheckIn_api.RoomName.RoomName;
import pl.coderslab.LetsCheckIn_api.RoomName.RoomNameRepository;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Controller
@RequiredArgsConstructor
@RequestMapping("/room/")
public class RoomController {

    private final RoomService roomService;
    private final ApartmentService apartmentService;
    private final RoomNameRepository roomNameRepository;

    @RequestMapping ("/add/{apartment_id}")
    public String addForm (@PathVariable Long apartment_id, Model model) {
        model.addAttribute("apartment",apartmentService.getById(apartment_id));
        model.addAttribute("room",new Room());
        List<RoomName> all = roomNameRepository.findAll();
        model.addAttribute("roomName",all);

        return "/room/add";
    }
    @RequestMapping (value = "/add/{apartment_id}", method = RequestMethod.POST)
    public String addPost (@PathVariable Long apartment_id,
                           Model model,
                           Room room,
                           @RequestParam List<MultipartFile> pictures) {
        roomService.saveRoom(room);
        if (pictures.get(0).getSize() != 0) {
            pictures.forEach(it -> {
                try {
                    roomService.addPictureAndSave(it, room, apartmentService.getById(apartment_id));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        return "redirect:/room/add/"+apartment_id;
    }



}
