package bg.softuni.pathfinder.config;

import bg.softuni.pathfinder.model.entity.enums.Role;
import bg.softuni.pathfinder.repository.UserRepository;
import bg.softuni.pathfinder.service.ApplicationUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;

//@Value ("${app.default.password}") String password -default password for all users - > defined in application.properties
@Configuration
public class ApplicationSecurityConfiguration {

    @Bean
    public SpringSecurityDialect securityDialect() {
        return new SpringSecurityDialect();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           SecurityContextRepository securityContextRepository) throws Exception {
        http.

                authorizeHttpRequests(authorize -> authorize.
                        requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll().
                        requestMatchers("/", "/about", "/users/login", "/users/register", "/users/login-error").permitAll().
                        requestMatchers("/admin").hasRole(Role.ADMIN.name()).
                        anyRequest().authenticated()
                )

                .formLogin(customizer -> customizer.
                        loginPage("/users/login").
                        usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY).
                        passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY).
                        defaultSuccessUrl("/").//use true argument if you always want to go there, otherwise go to previous page
                                failureForwardUrl("/users/login-error")
                )
                .logout(customizer -> customizer.
                        logoutUrl("/users/logout").
                        logoutSuccessUrl("/").
                        invalidateHttpSession(true))
                .securityContext(customizer -> customizer.
                        securityContextRepository(securityContextRepository));

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();}

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new ApplicationUserDetailsService(userRepository);
    }

    @Bean
    public SecurityContextRepository securityContextRepository() {
        return new DelegatingSecurityContextRepository(
                new RequestAttributeSecurityContextRepository(),
                new HttpSessionSecurityContextRepository()
        );
    }
}
