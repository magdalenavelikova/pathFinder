package bg.softuni.pathfinder.model.dto;

import bg.softuni.pathfinder.model.entity.UserEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter
@Setter
public class PictureDto {
    private String title;

    private String url;

    private UserEntity author;
}
