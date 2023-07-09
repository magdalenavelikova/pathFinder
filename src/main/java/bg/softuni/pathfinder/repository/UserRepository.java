package bg.softuni.pathfinder.repository;

import bg.softuni.pathfinder.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    public Optional<UserEntity> findByEmailAndPassword(String email, String password) ;

    Collection<Object> findByEmail(String email);
}
