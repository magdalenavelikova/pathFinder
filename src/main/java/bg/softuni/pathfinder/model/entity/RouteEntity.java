package bg.softuni.pathfinder.model.entity;

import bg.softuni.pathfinder.model.entity.enums.Level;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "routes")
public class RouteEntity extends BaseEntity {
    @Column(nullable = false)
    private String name;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;
    @Column(columnDefinition = "LONGTEXT",name = "gpx_coordinates", nullable = false)
  //  @Lob
    private String gpxCoordinates;
    @Enumerated(value = EnumType.STRING)
    private Level level;

    @Column(name = "video_url", nullable = false)
    private String video_url;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private UserEntity author;

    @ManyToMany
    private List<CategoryEntity> categories;
    @OneToMany(targetEntity = CommentEntity.class,mappedBy = "route")
    private Set<CommentEntity> comments;
}
