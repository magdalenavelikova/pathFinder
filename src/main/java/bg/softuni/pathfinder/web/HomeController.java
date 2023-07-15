package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.service.PictureService;
import bg.softuni.pathfinder.service.RouteService;
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
    public String home(Model model) {
        model.addAttribute("mostCommented", routeService.getMostCommentedRoute());
        model.addAttribute("pictures", pictureService.findAllUrls());
        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }
}
