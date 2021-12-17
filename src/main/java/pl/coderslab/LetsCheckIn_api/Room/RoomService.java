package pl.coderslab.LetsCheckIn_api.Room;

import org.springframework.web.multipart.MultipartFile;
import pl.coderslab.LetsCheckIn_api.Apartment.Apartment;
import pl.coderslab.LetsCheckIn_api.SearchDto.SearchDto;
import pl.coderslab.LetsCheckIn_api.Security.CurrentUser;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface RoomService {
    void saveRoom (Room room);
    void delete (Room room);
    void addPictureAndSave(List <MultipartFile> photos, Room room, Apartment apartment);
    List<Room> findByApartment (Apartment apartment);
    Room getById (Long roomId);
    List<Room> roomsFromSearch (SearchDto searchDto, CurrentUser currentUser);
}
