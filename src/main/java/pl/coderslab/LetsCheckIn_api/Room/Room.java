package pl.coderslab.LetsCheckIn_api.Room;


import lombok.Getter;
import lombok.Setter;
import pl.coderslab.LetsCheckIn_api.Apartment.Apartment;
import pl.coderslab.LetsCheckIn_api.Photo.Photo;

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

    private String name;
    private Integer persons;
    private Integer singleBed;
    private Integer doubleBed;
    private Integer coach;

    @ManyToOne
    private Apartment apartment;

    @OneToMany(mappedBy = "room")
    private List<Photo> photos;

    private BigDecimal roomPrice;
    private boolean withOwnBathroom;
    private boolean withKitchenAnex;
    private boolean interconnecting;
    private boolean isBalcony;


    public boolean getIsBalcony() {
        return isBalcony;
    }
}
