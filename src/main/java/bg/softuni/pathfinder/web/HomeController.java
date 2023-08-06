package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.model.dto.AppUserDetails;
import bg.softuni.pathfinder.service.ApplicationUserDetailsService;
import bg.softuni.pathfinder.service.PictureService;
import bg.softuni.pathfinder.service.RouteService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final RouteService routeService;
    private final PictureService pictureService;

    public HomeController(RouteService routeService, PictureService pictureService) {
        this.routeService = routeService;
        this.pictureService = pictureService;
    }

    @GetMapping("/")
    public String home(@AuthenticationPrincipal AppUserDetails appUserDetails, Model model) {
        model.addAttribute("mostCommented", routeService.getMostCommentedRoute());
        if(appUserDetails!=null){
            model.addAttribute("userId",appUserDetails.getId());
        }

        model.addAttribute("pictures", pictureService.findAllUrls());

        return "index";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin-page";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }
}
