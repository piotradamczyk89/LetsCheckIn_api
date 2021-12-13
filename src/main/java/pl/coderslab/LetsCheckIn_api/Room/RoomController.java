package pl.coderslab.LetsCheckIn_api.Room;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.coderslab.LetsCheckIn_api.Apartment.Apartment;
import pl.coderslab.LetsCheckIn_api.Apartment.ApartmentService;
import pl.coderslab.LetsCheckIn_api.RoomName.RoomNameRepository;
import pl.coderslab.LetsCheckIn_api.Security.CurrentUser;

import java.io.IOException;
import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/room/")
public class RoomController {

    private final RoomService roomService;
    private final ApartmentService apartmentService;
    private final RoomNameRepository roomNameRepository;

    @RequestMapping ("/add/{apartment_id}")//funkcja z linkiem po userze
    public String addForm (@PathVariable Long apartment_id, Model model) {
        model.addAttribute("apartment",apartmentService.getById(apartment_id));
        model.addAttribute("room",new Room());
        model.addAttribute("roomName",roomNameRepository.findAll());

        return "/room/add";
    }
    @RequestMapping (value = "/add/{apartment_id}", method = RequestMethod.POST)
    public String addPost (@PathVariable Long apartment_id,
                           Room room,
                           @RequestParam List<MultipartFile> pictures) {
        roomService.saveRoom(room);
        roomService.addPictureAndSave(pictures,room,apartmentService.getById(apartment_id));

        return "redirect:/room/add/"+apartment_id;
    }

    @RequestMapping("/listByApart/{apartmentId}")
    public String list (@AuthenticationPrincipal CurrentUser currentUser, Model model, @PathVariable Long apartmentId) {
        Apartment apartment = apartmentService.getById(apartmentId);
        model.addAttribute("rooms",roomService.findByApartment(apartment));
        model.addAttribute("apartment",apartment);
        return "/room/list";
    }

    @RequestMapping("/edit/{roomId}")
    public String editForm (@AuthenticationPrincipal CurrentUser currentUser, Model model, @PathVariable Long roomId) {
        Room room = roomService.getById(roomId);
        if (currentUser.getUser().getId()!=room.getApartment().getOwner().getId()) {
            return "/home";
        }
        model.addAttribute("room", room);
        model.addAttribute("roomName",roomNameRepository.findAll());
        return "/room/edit";
    }

    @RequestMapping(value = "/edit/{roomId}", method = RequestMethod.POST)
    public String editPost (Room room, @PathVariable Long roomId) {
        roomService.saveRoom(room);
        return "redirect:/room/listByApart/"+room.getApartment().getId();
    }
    @RequestMapping("/details/{roomId}")
    public String details (@AuthenticationPrincipal CurrentUser currentUser, Model model, @PathVariable Long roomId) {
        Room room = roomService.getById(roomId);
        model.addAttribute("room", room);
        if (currentUser!=null) {
            model.addAttribute("userId",currentUser.getUser().getId());
        }
        return "/room/details";
    }

    @RequestMapping("/delete/{roomId}")
    public String delete (@AuthenticationPrincipal CurrentUser currentUser, @PathVariable Long roomId){
        Room room = roomService.getById(roomId);
        if (room.getApartment().getOwner().getId()==currentUser.getUser().getId()) {
            roomService.delete(room);
        }
        return "redirect:/room/listByApart/"+room.getApartment().getId();
    }








}
