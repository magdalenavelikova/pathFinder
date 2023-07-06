package bg.softuni.pathfinder.model.entity;


import bg.softuni.pathfinder.model.entity.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "roles")
public class RoleEntity extends BaseEntity {

    @Enumerated(value = EnumType.STRING)
    private Role role;
}
