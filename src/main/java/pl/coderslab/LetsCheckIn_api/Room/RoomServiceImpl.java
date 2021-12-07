package pl.coderslab.LetsCheckIn_api.Room;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.coderslab.LetsCheckIn_api.Apartment.Apartment;
import pl.coderslab.LetsCheckIn_api.Photo.Photo;

import pl.coderslab.LetsCheckIn_api.Photo.PhotoService;
import pl.coderslab.LetsCheckIn_api.Utils.FileUploadUtil;

import java.io.IOException;

@Service
@RequiredArgsConstructor

public class RoomServiceImpl implements RoomService{

    private final RoomRepository repository;
    private final PhotoService photoService;


    @Override
    public void saveRoom(Room room) {
        repository.save(room);
    }

    @Override
    public void addPictureAndSave(MultipartFile photos, Room room) throws IOException {
        String uploadDir = "../LetsCheckIn_pictures/";
        Photo photo = new Photo();
        photo.setRoom(room);
        photoService.savePhoto(photo);
        FileUploadUtil.saveFile(uploadDir, photo.getId()+"", photos);
    }
}
