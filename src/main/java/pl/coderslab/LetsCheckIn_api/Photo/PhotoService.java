package pl.coderslab.LetsCheckIn_api.Photo;

import pl.coderslab.LetsCheckIn_api.Apartment.Apartment;
import pl.coderslab.LetsCheckIn_api.Room.Room;

import java.util.List;

public interface PhotoService {
    void savePhoto (Photo photo);
    void delete (Photo photo);
    List<Photo> findByApartment (Apartment apartment);
    List<Photo> findByRoom (Room room);
}
