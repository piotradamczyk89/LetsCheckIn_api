package pl.coderslab.LetsCheckIn_api.Room;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.coderslab.LetsCheckIn_api.Apartment.Apartment;
import pl.coderslab.LetsCheckIn_api.Photo.Photo;

import pl.coderslab.LetsCheckIn_api.Photo.PhotoService;
import pl.coderslab.LetsCheckIn_api.Reservation.ReservationService;
import pl.coderslab.LetsCheckIn_api.Utils.FileUploadUtil;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor

public class RoomServiceImpl implements RoomService{

    private final RoomRepository roomRepository;
    private final PhotoService photoService;
    private final ReservationService reservationService;


    @Override
    public void saveRoom(Room room) {
        roomRepository.save(room);
    }

    @Override
    public void addPictureAndSave(List <MultipartFile> photos, Room room, Apartment apartment) {
        if (photos.get(0).getSize() != 0) {
            photos.forEach(it -> {
                String uploadDir = "src/main/webapp/img/LetsCheckIn_pictures";
                Photo photo = new Photo();
                photo.setRoom(room);
                photo.setApartment(apartment);
                photoService.savePhoto(photo);
                try {
                    FileUploadUtil.saveFile(uploadDir, photo.getId()+"", it);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } else {
            return;
        }
    }

    @Override
    public List<Room> findByApartment(Apartment apartment) {
        return roomRepository.findByApartment(apartment);
    }

    @Override
    public Room getById(Long roomId) {
        return roomRepository.getById(roomId);
    }

    @Override
    public void delete(Room room) {
        photoService.findByRoom(room).forEach(it -> photoService.delete(it));
        reservationService.findByRoom(room).forEach(it->reservationService.delete(it));
        roomRepository.delete(room);
    }

    @Override
    public List<Room> roomsFromSearch(String country, String city, Integer person, LocalDate startDate, LocalDate endDate, Long userId) {
        return roomRepository.roomsFromSearch(country,city,person,startDate,endDate,userId);
    }
}
