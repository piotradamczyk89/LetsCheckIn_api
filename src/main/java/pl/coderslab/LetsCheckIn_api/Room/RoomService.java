package pl.coderslab.LetsCheckIn_api.Room;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface RoomService {
    void saveRoom (Room room);
    void addPictureAndSave(MultipartFile photos, Room room) throws IOException;
}
