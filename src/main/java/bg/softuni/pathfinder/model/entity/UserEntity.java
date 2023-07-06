package bg.softuni.pathfinder.model.entity;

import bg.softuni.pathfinder.model.entity.enums.Level;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Column(nullable = false,
            unique = true)
    private String username;

    @Column(nullable = false)
    private String password;
    @Column(nullable = false,
            unique = true)
    private String email;
    @Column(nullable = false)
    private Integer age;
    @Column(name = "full_name", nullable = false)
    private String fullName;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<RoleEntity> roles = new HashSet<>();
    @Enumerated(value = EnumType.STRING)
    private Level level;

}
