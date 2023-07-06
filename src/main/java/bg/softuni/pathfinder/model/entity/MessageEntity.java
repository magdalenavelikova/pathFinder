package bg.softuni.pathfinder.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@Getter
@Setter
@NoArgsConstructor
public class MessageEntity extends BaseEntity {
    @Column(name = "date_time")
    private LocalDateTime dateTime;
    @Column(name = "text_content", columnDefinition = "TEXT")
    private String textContent;

    @ManyToOne
    private UserEntity author;
    @ManyToOne
    private UserEntity recipient;

}
