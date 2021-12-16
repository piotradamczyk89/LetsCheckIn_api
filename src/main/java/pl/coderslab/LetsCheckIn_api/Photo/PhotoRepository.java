package pl.coderslab.LetsCheckIn_api.Photo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.LetsCheckIn_api.Apartment.Apartment;
import pl.coderslab.LetsCheckIn_api.Room.Room;

import java.util.List;

public interface PhotoRepository extends JpaRepository <Photo,Long> {

    List<Photo> findByApartment (Apartment apartment);
    List<Photo> findByRoom (Room room);
}
