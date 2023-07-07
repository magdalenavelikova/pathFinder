package bg.softuni.pathfinder.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class MostCommentedRouteDto {
    private Long id;

    private String name;

    private String description;

    private String gpxCoordinates;

     List<PictureDto> pictures;
}
