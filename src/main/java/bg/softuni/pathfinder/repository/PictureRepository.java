package bg.softuni.pathfinder.repository;

import bg.softuni.pathfinder.model.entity.PictureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository extends JpaRepository<PictureEntity,Long> {
}
