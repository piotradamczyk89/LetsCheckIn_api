package pl.coderslab.LetsCheckIn_api.Photo;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.LetsCheckIn_api.Apartment.Apartment;
import pl.coderslab.LetsCheckIn_api.Room.Room;

import javax.persistence.*;

@Entity
@Getter
@Setter

public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Apartment apartment;

    @ManyToOne
    private Room room;
}
