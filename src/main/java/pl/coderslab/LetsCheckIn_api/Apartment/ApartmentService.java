package pl.coderslab.LetsCheckIn_api.Apartment;

import org.springframework.web.multipart.MultipartFile;
import pl.coderslab.LetsCheckIn_api.SearchDto.SearchDto;
import pl.coderslab.LetsCheckIn_api.SearchDto.SearchLongDto;
import pl.coderslab.LetsCheckIn_api.Security.CurrentUser;
import pl.coderslab.LetsCheckIn_api.User.User;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Currency;
import java.util.List;


public interface ApartmentService {
    List<Apartment> apartmentsFromSearch (SearchDto searchDto, CurrentUser currentUser);
    List<Apartment> apartmentsLongFromSearch (SearchLongDto searchLongDto, CurrentUser currentUser);
    void saveApartment (Apartment apartment);
    void delete (Apartment apartment);
    void addPictureAndSave (List <MultipartFile> photos, Apartment apartment);
    Apartment getById (Long apartment_id);
    List<Apartment> findByOwner (User owner);
}
