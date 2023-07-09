package bg.softuni.pathfinder.model.dto;



import bg.softuni.pathfinder.model.validations.FieldMatch;
import bg.softuni.pathfinder.model.validations.UniqueUserEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor

@FieldMatch(first = "password",
second = "confirmPassword",
message = "Passwords do not match")
public class UserRegisterDto {
    private String username;
    private String fullName;


    @NotEmpty(message="User email should be provided.")
    @Email(message="User email should be valid.")
    @UniqueUserEmail(message="User email should be unique.")
    private String email;

    @Min(value = 10, message = "Age must be above 10 ")
    private Integer age;
    @NotEmpty
    @Size(min = 5, max = 10)
    private String password;
    @NotEmpty
    @Size(min = 5, max = 10)
    private String confirmPassword;



}
