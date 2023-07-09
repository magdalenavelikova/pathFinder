package bg.softuni.pathfinder.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import java.time.LocalDateTime;

import static java.time.format.DateTimeFormatter.ofPattern;


@Configuration
public class ApplicationBeanConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        Converter<String, LocalDateTime> localDateConverter = mappingContext ->
                LocalDateTime.parse(mappingContext.getSource(), ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
        Converter<LocalDateTime, String> fromLocalDateConverter = mappingContext -> mappingContext.getSource().toString();
        modelMapper.addConverter(localDateConverter);

        return modelMapper;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }
}
