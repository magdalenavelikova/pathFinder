package bg.softuni.pathfinder.model.entity;

import bg.softuni.pathfinder.model.entity.enums.Category;
import jakarta.persistence.*;
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
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Category name;
}
