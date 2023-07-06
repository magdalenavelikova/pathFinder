package bg.softuni.pathfinder.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class CommentEntity extends BaseEntity {
    private Boolean approved;
    @Column(name = "date_time")
    private LocalDateTime created;
   @Column(name = "text_content", columnDefinition = "LONGTEXT")
    private String textContent;
    @ManyToOne
    private UserEntity author;
    @ManyToOne
    private RouteEntity route;

}
