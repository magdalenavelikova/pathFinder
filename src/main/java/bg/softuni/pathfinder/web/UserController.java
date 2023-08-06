package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.model.dto.UserProfileDto;
import bg.softuni.pathfinder.model.dto.UserRegisterDto;
import bg.softuni.pathfinder.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final SecurityContextRepository securityContextRepository;

    public UserController(UserService userService, SecurityContextRepository securityContextRepository) {
        this.userService = userService;
        this.securityContextRepository = securityContextRepository;
    }

    @ModelAttribute("userRegisterDto")
    public UserRegisterDto initUserModel() {
        return new UserRegisterDto();
    }

//    @ModelAttribute("userLoginDto")
//    public UserLoginDto loginUserModel() {
//        return new UserLoginDto();
//    }

    @ModelAttribute("userProfileDto")
    public UserProfileDto profileModel() {
        return new UserProfileDto();
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

//    @PostMapping("/register")
//    public String register(
//            @Valid UserRegisterDto userRegisterDto,
//            BindingResult bindingResult,
//            RedirectAttributes redirectAttributes) {
//        if (bindingResult.hasErrors()) {
//            redirectAttributes.addFlashAttribute("userRegisterDto", userRegisterDto);
//            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterDto", bindingResult);
//            return "redirect:/register";
//        }
//        userService.registerAndLogin(userRegisterDto);
//        return "redirect:/";
//    }
@PostMapping("/register")
public String registerNewUser(@Valid UserRegisterDto userRegisterDto,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes,
                              HttpServletRequest request,
                              HttpServletResponse response) {
    if (bindingResult.hasErrors()) {
        redirectAttributes.addFlashAttribute("userRegisterDto", userRegisterDto);
        redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterDto", bindingResult);
        return "redirect:/users/register";
    }

    userService.registerAndLogin(userRegisterDto, successfulAuth -> {
        // populating security context
        SecurityContextHolderStrategy strategy = SecurityContextHolder.getContextHolderStrategy();

        SecurityContext context = strategy.createEmptyContext();
        context.setAuthentication(successfulAuth);
        strategy.setContext(context);

        securityContextRepository.saveContext(context, request, response);
    });

    return "redirect:/";
}

    @GetMapping("/login")
    public String login() {
        //  userService.encodeOldPassword();
        return "login";
    }

    @PostMapping("/login-error")
    public String onFailedLogin(
            @ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String username,
            RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY,
                username);

        redirectAttributes.addFlashAttribute("bad_credentials", true);

        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String profile(Principal principal, Model model) {
        model.addAttribute("userProfile",
                userService.getProfile(principal.getName()));

        return "profile";
    }
}

