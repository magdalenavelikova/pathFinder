package bg.softuni.pathfinder.model.dto;

import bg.softuni.pathfinder.model.entity.CategoryEntity;
import bg.softuni.pathfinder.model.entity.enums.Category;
import bg.softuni.pathfinder.model.entity.enums.Level;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class AddRouteDto {
    @NotEmpty
    @Size(min = 3, max = 20, message = "Route name mus be between 3 and 20 charachters")
    private String name;

    @NotEmpty
    private String description;
    private MultipartFile gpxCoordinates;
    @NotNull
    private Level level;
    @Max(11)
    private String videoUrl;
    @NotNull
    private List<Category> categories;
    private Long authorId;
}

