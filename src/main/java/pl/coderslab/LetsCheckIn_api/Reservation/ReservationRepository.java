package pl.coderslab.LetsCheckIn_api.Reservation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
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
    List<Reservation> findByRoom(Room room);
    List<Reservation> findByEndDateBeforeAndTenant(LocalDate actualDate, User tenant);

    List<Reservation> findByEndDateAfterAndTenant(LocalDate actualDate, User tenant);


   @Query("SELECT r from Reservation r where r.apartment.id=?1 and (r.startDate not between ?2 and ?3) and (r.endDate not between ?2 and ?3)")
    List<Reservation> checkReservation (Long apartmentId, LocalDate startDate, LocalDate endDate);

   @Query("SELECT r from Reservation r join r.apartment a where a.owner=?1 and a=?2 and r.endDate>=current_date order by r.startDate")
   List<Reservation> allOwnerActualApartmentReservation (User owner, Apartment apartment);


   @Query(value = "SELECT r.id,r.end_date,r.start_date,r.apartment_id,r.room_id,r.name,r.tenant_id from reservation r join apartment a on a.id = r.apartment_id where (Select count(b.id) from bill b where b.reservation_id=r.id and b.is_paid=false and b.expire_date<current_date)>0 and a.owner_id=?1 order by r.start_date",nativeQuery = true)
   Slice<Reservation> notPaidAfterDeadline (Long ownerId, Pageable pageable);


   @Query(value = "SELECT r.id,r.end_date,r.start_date,r.apartment_id,r.room_id,r.name,r.tenant_id" +
           " from reservation r join apartment a on a.id = r.apartment_id where" +
           "(Select count(b.id) from bill b where b.reservation_id=r.id and b.is_paid=false and b.expire_date>current_date)>0 and" +
           "(Select count(b.id) from bill b where b.reservation_id=r.id and b.is_paid=false and b.expire_date<current_date)=0 " +
           "and a.owner_id=?1 and a.id=?2 order by r.start_date;",nativeQuery = true)
   Slice<Reservation> notPaidBeforeDeadline (Long ownerId, Long apartmentId, Pageable pageable);


    @Query(value = "SELECT r.id,r.end_date,r.start_date,r.apartment_id,r.room_id,r.name,r.tenant_id from reservation r join apartment a on a.id = r.apartment_id where (Select count(b.id) from bill b where b.reservation_id=r.id and b.is_paid=false)>0 and a.owner_id=?1 order by r.start_date",nativeQuery = true)
   List <Reservation> notPaidReservation (Long ownerId);


   @Query(value = "SELECT r.id,r.apartment_id,r.end_date,r.name,r.room_id,r.start_date,r.tenant_id " +
           "from reservation r join apartment a on a.id = r.apartment_id where" +
           "(Select count(b.id) from bill b where b.reservation_id=r.id and b.is_paid=false ) =0 " +
           "and a.owner_id=?1 and a.id=?2 and r.end_date<current_date order by r.start_date",nativeQuery = true)
   Slice<Reservation> oldApartmentReservation (Long ownerId, Long apartmentId, Pageable pageable);


}
