package pl.coderslab.LetsCheckIn_api.Apartment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import pl.coderslab.LetsCheckIn_api.Photo.Photo;
import pl.coderslab.LetsCheckIn_api.Photo.PhotoRepository;
import pl.coderslab.LetsCheckIn_api.Photo.PhotoService;
import pl.coderslab.LetsCheckIn_api.Utils.FileUploadUtil;

import java.io.IOException;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ApartmentServiceImpl implements ApartmentService{

    private final ApartmentRepository apartmentRepository;
    private final PhotoService photoService;

    @Override
    public List<Apartment> apartmentsFromSearch(String country, String city, Long person) {
        return apartmentRepository.apartmentsFromSearch(country,city,person);
    }

    @Override
    public void saveApartment(Apartment apartment) {
        apartmentRepository.save(apartment);
    }

    @Override
    public void addPictureAndSave(MultipartFile photos, Apartment apartment) throws IOException {
        String uploadDir = "src/main/webapp/img/LetsCheckIn_pictures";
        Photo photo = new Photo();
        photo.setApartment(apartment);
        photoService.savePhoto(photo);
        FileUploadUtil.saveFile(uploadDir, photo.getId()+"", photos);
    }

    @Override
    public Apartment getById(Long apartment_id) {
        return apartmentRepository.getById(apartment_id);
    }
}
