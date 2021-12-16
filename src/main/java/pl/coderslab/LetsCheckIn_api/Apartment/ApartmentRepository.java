package pl.coderslab.LetsCheckIn_api.Apartment;

import org.apache.tomcat.jni.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.LetsCheckIn_api.User.User;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface ApartmentRepository extends JpaRepository <Apartment,Long> {
//
    @Query ("select a from Apartment a left join a.reservations rr " +
            "where a.country.name=?1" +
            " and a.rentWay.id=1l" +
            " and a.city=?2" +
            " and (SELECT SUM (r.persons) from Room r where r.apartment.id=a.id)=?3" +
            " and (rr.size =0 or ((rr.startDate not between ?4 and ?5)" +
            " and (rr.endDate not between ?4 and ?5))) and a.owner.id<>?6" +
            " group by a.id")
    List<Apartment> apartmentsFromSearch (String country, String city,
                                          Long person, LocalDate startDate, LocalDate endDate, Long userId);
    List<Apartment> findByOwner (User owner);
}
