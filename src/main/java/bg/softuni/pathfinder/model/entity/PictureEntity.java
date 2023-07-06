package bg.softuni.pathfinder.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pictures")
@NoArgsConstructor
@Getter
@Setter

public class PictureEntity extends BaseEntity {
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String url;
    @ManyToOne(optional = false)
    private UserEntity author;
    @ManyToOne(optional = false)
    private RouteEntity route;
}
