package pl.coderslab.LetsCheckIn_api.Photo;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.LetsCheckIn_api.Apartment.Apartment;
import pl.coderslab.LetsCheckIn_api.Room.Room;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoService{

    private final PhotoRepository photoRepository;

    @Override
    public void savePhoto(Photo photo) {
       photoRepository.save(photo);

    }

    @Override
    public void delete(Photo photo) {
        photoRepository.delete(photo);
    }

    @Override
    public List<Photo> findByApartment(Apartment apartment) {
        return photoRepository.findByApartment(apartment);
    }

    @Override
    public List<Photo> findByRoom(Room room) {
        return photoRepository.findByRoom(room);
    }
}
