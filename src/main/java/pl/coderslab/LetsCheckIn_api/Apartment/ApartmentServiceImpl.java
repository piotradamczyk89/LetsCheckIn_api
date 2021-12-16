package pl.coderslab.LetsCheckIn_api.Apartment;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import pl.coderslab.LetsCheckIn_api.Photo.Photo;
import pl.coderslab.LetsCheckIn_api.Photo.PhotoRepository;
import pl.coderslab.LetsCheckIn_api.Photo.PhotoService;
import pl.coderslab.LetsCheckIn_api.Reservation.ReservationService;
import pl.coderslab.LetsCheckIn_api.Room.RoomRepository;
import pl.coderslab.LetsCheckIn_api.Room.RoomService;
import pl.coderslab.LetsCheckIn_api.User.User;
import pl.coderslab.LetsCheckIn_api.Utils.FileUploadUtil;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ApartmentServiceImpl implements ApartmentService{

    private final ApartmentRepository apartmentRepository;
    private final PhotoService photoService;
    private final RoomService roomService;
    private final ReservationService reservationService;

    @Override
    public List<Apartment> apartmentsFromSearch(String country, String city,
                                                Long person, LocalDate startDate, LocalDate endDate, Long userId) {
        return apartmentRepository.apartmentsFromSearch(country,city,person,startDate,endDate,userId);
    }

    @Override
    public void saveApartment(Apartment apartment) {
        apartmentRepository.save(apartment);
    }

    @Override
    public void addPictureAndSave(List <MultipartFile> photos, Apartment apartment) {
        if (photos.get(0).getSize() != 0) {
            photos.forEach(it -> {
               String uploadDir = "src/main/webapp/img/LetsCheckIn_pictures";
                Photo photo = new Photo();
                photo.setApartment(apartment);
                photoService.savePhoto(photo);
                try {
                    FileUploadUtil.saveFile(uploadDir, photo.getId()+"", it);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } else {
            return;
        }
    }

    @Override
    public Apartment getById(Long apartment_id) {
        return apartmentRepository.getById(apartment_id);
    }

    @Override
    public List<Apartment> findByOwner(User owner) {
        return apartmentRepository.findByOwner(owner);
    }

    @Override
    public void delete(Apartment apartment) {
        roomService.findByApartment(apartment).forEach(it -> roomService.delete(it));
        reservationService.findByApartment(apartment).forEach(it->reservationService.delete(it));
        photoService.findByApartment(apartment).forEach(it -> photoService.delete(it));
        apartmentRepository.delete(apartment);
    }
}
