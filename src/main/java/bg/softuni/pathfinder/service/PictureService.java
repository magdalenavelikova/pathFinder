package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.model.entity.PictureEntity;
import bg.softuni.pathfinder.repository.PictureRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class PictureService {
    private final PictureRepository pictureRepository;

    public PictureService(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }



    public List<String> findAllUrls() {
        return   pictureRepository.findAll()
                .stream()
                .map(PictureEntity::getUrl)
                .collect(Collectors.toList());
    }
}
