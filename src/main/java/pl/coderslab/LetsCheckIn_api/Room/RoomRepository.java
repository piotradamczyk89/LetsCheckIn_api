package pl.coderslab.LetsCheckIn_api.Room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.LetsCheckIn_api.Apartment.Apartment;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository <Room,Long> {

    List<Room> findByApartment (Apartment apartment);
    @Query("SELECT r from Room r left join r.reservations re where r.apartment.rentWay.id=3" +
            " and r.apartment.country.name=?1 " +
            "and r.apartment.city=?2 and r.persons=?3 " +
            "and re.size =0 " +
            "or ((re.startDate not between ?4 and ?5) and (re.endDate not between ?4 and ?5)) and r.apartment.owner.id<>?6 group by r.id ")
    List<Room> roomsFromSearch (String country, String city, Integer person, LocalDate startDate, java.time.LocalDate endDate, Long userId);
}
