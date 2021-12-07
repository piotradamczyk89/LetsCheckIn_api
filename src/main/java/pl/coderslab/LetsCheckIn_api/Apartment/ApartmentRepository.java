package pl.coderslab.LetsCheckIn_api.Apartment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ApartmentRepository extends JpaRepository <Apartment,Long> {
//
    @Query ("select a from Apartment a where a.country=?1 and a.city=?2 and (SELECT SUM (r.persons) from Room r where r.apartment.id=a.id)=?3 ")
    List<Apartment> apartmentsFromSearch (String country,String city, Long person);
}
