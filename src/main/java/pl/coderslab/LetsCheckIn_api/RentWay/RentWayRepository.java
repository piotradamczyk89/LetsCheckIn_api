package pl.coderslab.LetsCheckIn_api.RentWay;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentWayRepository extends JpaRepository <RentWay, Long>{
}
