package pl.coderslab.LetsCheckIn_api.Reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.LetsCheckIn_api.Apartment.Apartment;
import pl.coderslab.LetsCheckIn_api.Room.Room;
import pl.coderslab.LetsCheckIn_api.User.User;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface ReservationRepository extends JpaRepository <Reservation,Long>{

    List<Reservation> findByApartment (Apartment apartment);

    List <Reservation> findByRoom (Room room);

    List<Reservation> findByEndDateBeforeAndTenant(LocalDate actualDate, User tenant);

    List<Reservation> findByEndDateAfterAndTenant(LocalDate actualDate, User tenant);


   @Query("SELECT r from Reservation r where r.apartment.id=?1 and (r.startDate not between ?2 and ?3) and (r.endDate not between ?2 and ?3)")
    List<Reservation> checkReservation (Long apartmentId, LocalDate startDate, LocalDate endDate);

   @Query("SELECT r from Reservation r join r.apartment a where a.owner=?1 and a=?2 order by r.startDate")
   List<Reservation> allOwnerApartmentReservation (User owner, Apartment apartment);

   @Query("select r from Reservation r join r.bills b where b.name='Advance payment' and b.isPaid=false and r.apartment=?1 and r.tenant=?2 order by r.startDate")
   List<Reservation> apartmentReservationWithoutConfirm (Apartment apartment, User tenant);

}
