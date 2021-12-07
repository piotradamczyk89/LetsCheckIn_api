package pl.coderslab.LetsCheckIn_api.Photo;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoService{

    private final PhotoRepository photoRepository;

    @Override
    public void savePhoto(Photo photo) {
       photoRepository.save(photo);

    }
}
