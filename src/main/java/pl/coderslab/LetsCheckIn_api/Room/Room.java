package pl.coderslab.LetsCheckIn_api.Room;


import lombok.Getter;
import lombok.Setter;
import pl.coderslab.LetsCheckIn_api.Apartment.Apartment;
import pl.coderslab.LetsCheckIn_api.Photo.Photo;
import pl.coderslab.LetsCheckIn_api.Reservation.Reservation;
import pl.coderslab.LetsCheckIn_api.RoomName.RoomName;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private RoomName roomName;
    private Integer persons;
    private Integer singleBed;
    private Integer doubleBed;
    private Integer coach;

    @ManyToOne
    private Apartment apartment;

    @OneToMany(mappedBy = "room")
    private List<Photo> photos;
    @OneToMany(mappedBy = "room")
    private List<Reservation> reservations;



    private BigDecimal roomPrice;
    private boolean withOwnBathroom;
    private boolean withKitchenAnex;
    private boolean interconnecting;
    private boolean isBalcony;
    private String description;


    public boolean getIsBalcony() {
        return isBalcony;
    }
}
