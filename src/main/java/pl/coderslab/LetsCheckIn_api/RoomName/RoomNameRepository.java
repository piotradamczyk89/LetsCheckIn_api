package pl.coderslab.LetsCheckIn_api.RoomName;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomNameRepository extends JpaRepository <RoomName, Long> {
}
