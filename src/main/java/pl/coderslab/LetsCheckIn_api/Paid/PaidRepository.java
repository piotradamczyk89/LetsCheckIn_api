package pl.coderslab.LetsCheckIn_api.Paid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaidRepository extends JpaRepository <Paid, Long> {

}
