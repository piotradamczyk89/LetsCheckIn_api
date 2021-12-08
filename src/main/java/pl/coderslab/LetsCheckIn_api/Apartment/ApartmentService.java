package pl.coderslab.LetsCheckIn_api.Apartment;

import org.springframework.web.multipart.MultipartFile;
import pl.coderslab.LetsCheckIn_api.User.User;

import java.io.IOException;
import java.util.List;


public interface ApartmentService {
    List<Apartment> apartmentsFromSearch (String country,String city, Long person);
    void saveApartment (Apartment apartment);
    void addPictureAndSave (MultipartFile photo, Apartment apartment) throws IOException;
    Apartment getById (Long apartment_id);
    List<Apartment> findByOwner (User owner);
}
