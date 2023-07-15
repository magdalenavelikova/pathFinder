package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.model.dto.UserLoginDto;
import bg.softuni.pathfinder.model.dto.UserRegisterDto;
import bg.softuni.pathfinder.model.entity.UserEntity;
import bg.softuni.pathfinder.model.entity.enums.Level;
import bg.softuni.pathfinder.repository.UserRepository;
import bg.softuni.pathfinder.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {
    private final ModelMapper mapper;
    private final UserRepository userRepository;

    private final CurrentUser currentUser;
    private final PasswordEncoder passwordEncoder;
    private final Logger LOGGER = (Logger) LoggerFactory.getLogger(UserService.class);

    public UserService(ModelMapper mapper, UserRepository userRepository, CurrentUser currentUser, PasswordEncoder passwordEncoder) {
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.passwordEncoder = passwordEncoder;
    }


    public void registerAndLogin(UserRegisterDto userRegisterDto) {

        UserEntity user = mapper.map(userRegisterDto, UserEntity.class);
        user.setLevel(Level.BEGINNER);
        userRepository.save(user);
    }

    public boolean login(UserLoginDto userLoginDto) {
        Optional<UserEntity> userOpt = userRepository.findByEmailAndPassword(userLoginDto.getEmail(), userLoginDto.getPassword());
        if (userOpt.isEmpty()) {
            LOGGER.info("User with email [{}] not found.", userLoginDto.getEmail());
            return false;
        }
        UserEntity user = userOpt.get();
        setCurrent(user);
        return true;
    }

    public void setCurrent(UserEntity userEntity) {
        currentUser.setId(userEntity.getId());
        currentUser.setLoggedIn(true);
        currentUser.setEmail(userEntity.getEmail());
        currentUser.setName(userEntity.getFullName());

    }


    public void logout() {
        currentUser.clear();
    }
}
