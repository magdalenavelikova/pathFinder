package bg.softuni.pathfinder.repository;

import bg.softuni.pathfinder.model.entity.RoleEntity;
import bg.softuni.pathfinder.model.entity.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<RoleEntity,Long> {

    Optional<RoleEntity> findByRole(Role role);
}
