package pl.coderslab.LetsCheckIn_api.Room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.LetsCheckIn_api.Apartment.Apartment;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository <Room,Long> {
    List<Room> findByApartment (Apartment apartment);
}
