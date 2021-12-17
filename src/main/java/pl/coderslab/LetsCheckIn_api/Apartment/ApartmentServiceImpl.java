package pl.coderslab.LetsCheckIn_api.Apartment;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import pl.coderslab.LetsCheckIn_api.Photo.Photo;
import pl.coderslab.LetsCheckIn_api.Photo.PhotoRepository;
import pl.coderslab.LetsCheckIn_api.Photo.PhotoService;
import pl.coderslab.LetsCheckIn_api.Reservation.ReservationService;
import pl.coderslab.LetsCheckIn_api.Room.RoomRepository;
import pl.coderslab.LetsCheckIn_api.Room.RoomService;
import pl.coderslab.LetsCheckIn_api.SearchDto.SearchDto;
import pl.coderslab.LetsCheckIn_api.SearchDto.SearchLongDto;
import pl.coderslab.LetsCheckIn_api.Security.CurrentUser;
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

    @Override
    public List<Apartment> apartmentsFromSearch(SearchDto searchDto, CurrentUser currentUser) {
        Long userId = -1L;
        if (currentUser != null) {
            userId = currentUser.getUser().getId();
        }
        return apartmentRepository.apartmentsFromSearch(searchDto.getCountry(),
                searchDto.getCity(),
                searchDto.getPerson().longValue(),
                searchDto.getStartDate(),
                searchDto.getEndDate(),
                userId);
    }

    @Override
    public List<Apartment> apartmentsLongFromSearch(SearchLongDto searchLongDto, CurrentUser currentUser) {
        Long userId = -1L;
        if (currentUser != null) {
            userId = currentUser.getUser().getId();
        }
        return apartmentRepository.apartmentsLongFromSearch(searchLongDto.getCountry(),
                searchLongDto.getCity(),
                searchLongDto.getArea(),
                searchLongDto.getStartDate(),
                searchLongDto.getEndDate(),
                userId);
    }
}
