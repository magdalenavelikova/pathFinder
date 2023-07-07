package bg.softuni.pathfinder.repository;

import bg.softuni.pathfinder.model.entity.RouteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<RouteEntity, Long> {
    @Query("select r FROM RouteEntity  r ORDER BY SIZE(r.comments) DESC LIMIT 1")
    RouteEntity findMostCommentedRoute();
}
