package pl.coderslab.LetsCheckIn_api.Apartment;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import pl.coderslab.LetsCheckIn_api.DtoApartmentAndRoom.DtoApartmentAndRoom;
import pl.coderslab.LetsCheckIn_api.Photo.Photo;
import pl.coderslab.LetsCheckIn_api.Photo.PhotoRepository;
import pl.coderslab.LetsCheckIn_api.Photo.PhotoService;
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
  //              String uploadDir = "/home/piotr/CodersLAB/LetsCheckIn_pictures";
                Photo photo = new Photo();
  //              String filename = StringUtils.cleanPath(it.getOriginalFilename());
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
        apartmentRepository.delete(apartment);
    }
}
