package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.model.dto.UserProfileDto;
import bg.softuni.pathfinder.model.dto.UserRegisterDto;
import bg.softuni.pathfinder.model.entity.RoleEntity;
import bg.softuni.pathfinder.model.entity.UserEntity;
import bg.softuni.pathfinder.model.entity.enums.Level;
import bg.softuni.pathfinder.model.entity.enums.Role;
import bg.softuni.pathfinder.repository.UserRepository;
import bg.softuni.pathfinder.repository.UserRoleRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import java.util.function.Consumer;


@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final Logger LOGGER = (Logger) LoggerFactory.getLogger(UserService.class);
    private final UserDetailsService userDetailsService;

    public UserService(ModelMapper mapper, UserRepository userRepository, PasswordEncoder passwordEncoder, PasswordEncoder passwordEncoder1, UserRoleRepository userRoleRepository, UserDetailsService userDetailsService) {
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder1;
        this.userRoleRepository = userRoleRepository;

        this.userDetailsService = userDetailsService;
    }

    public void registerAndLogin(UserRegisterDto userRegisterDto, Consumer<Authentication> successfulLoginProcessor) {
        UserEntity user = mapper.map(userRegisterDto, UserEntity.class);
        user.setLevel(Level.BEGINNER);
        var rowPassword = user.getPassword();
        user.setPassword(passwordEncoder.encode(rowPassword));
        Optional<RoleEntity> role = userRoleRepository.findByRole(Role.USER);
        user.setRoles(List.of(role.get()));
        user.setLevel(Level.BEGINNER);
        userRepository.save(user);
        UserDetails userDetails = userDetailsService.loadUserByUsername(userRegisterDto.getEmail());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                userDetails.getPassword(),
                userDetails.getAuthorities()
        );

        successfulLoginProcessor.accept(authentication);
    }









//call it only once to passed to passwordEncoder
//    public void encodeOldPassword() {
//        userRepository.findAll().stream().forEach(user->{
//            user.setPassword(passwordEncoder.encode(user.getPassword()));
//            userRepository.save(user);
//        });
//        System.out.println();
//    }


    public UserProfileDto getProfile(String username) {
        Optional<UserEntity> user = userRepository.findByUsername(username);

        if (user.isEmpty()) {
            LOGGER.info("User with username [{}] not found.", username);
            return null;
        }

        return mapper.map(user.get(), UserProfileDto.class);
    }


}
