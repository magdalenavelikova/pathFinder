package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.model.dto.AddRouteDto;
import bg.softuni.pathfinder.service.RouteService;
import bg.softuni.pathfinder.util.CurrentUser;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequestMapping("/routes")
public class RouteController {
    private final RouteService routeService;
    private final CurrentUser currentUser;

    public RouteController(RouteService routeService, CurrentUser currentUser) {
        this.routeService = routeService;
        this.currentUser = currentUser;
    }

    @GetMapping()
    public String allRoutes(Model model) {

        model.addAttribute("allRoutes", routeService.findAll());
        return "routes";
    }
    @ModelAttribute("addRouteDto")
    public AddRouteDto routeModel() {
        return new AddRouteDto();
    }

    @GetMapping("/add")
    public String addRoute(){

        return "add-route";
    }
    @PostMapping("/add")
    public String addRoute(@Valid AddRouteDto addRouteDto,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) throws IOException {
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("addRouteDto", addRouteDto);
            return "redirect: add";
        }
        boolean isSaved = routeService.addRoute(addRouteDto, currentUser.getId());
        return "redirect: routes";

    }
}
