package bg.softuni.pathfinder.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "categories")
public class CategoryEntity extends BaseEntity {

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;
    @Column(nullable = false)
    private String name;
}
