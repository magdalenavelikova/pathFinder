package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.model.dto.AddRouteDto;
import bg.softuni.pathfinder.model.dto.MostCommentedRouteDto;
import bg.softuni.pathfinder.model.dto.RouteDto;
import bg.softuni.pathfinder.model.entity.CategoryEntity;
import bg.softuni.pathfinder.model.entity.RouteEntity;
import bg.softuni.pathfinder.model.entity.UserEntity;
import bg.softuni.pathfinder.repository.CategoryRepository;
import bg.softuni.pathfinder.repository.RouteRepository;
import bg.softuni.pathfinder.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RouteService {
    private final RouteRepository routeRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;


    public RouteService(RouteRepository routeRepository, UserRepository userRepository, CategoryRepository categoryRepository, ModelMapper mapper) {
        this.routeRepository = routeRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;

        this.mapper = mapper;
    }


    public MostCommentedRouteDto getMostCommentedRoute() {
        return mapper.map(routeRepository.findMostCommentedRoute(), MostCommentedRouteDto.class);

    }

    public List<RouteDto> findAll() {
        return routeRepository.findAll().stream().map(route -> {
            RouteDto routeDto = mapper.map(route, RouteDto.class);
            routeDto.setPictureURL(
                    route.getPictures().isEmpty() ? "/images/pic4.jpg" :
                            route.getPictures().get(0).getUrl());
            return routeDto;
        }).collect(Collectors.toList());
    }

    public boolean addRoute(AddRouteDto addRouteDto, Long id) throws IOException {
        RouteEntity route = mapper.map(addRouteDto, RouteEntity.class);
        route.setGpxCoordinates(new String(addRouteDto.getGpxCoordinates().getBytes()));
        List<CategoryEntity> categories = addRouteDto.getCategories()
                .stream()
                .map(categoryRepository::findByName)
                .collect(Collectors.toList());
        route.setCategories(categories);
        Optional<UserEntity> author = userRepository.findById(id);
        if (author.isPresent()) {
            route.setAuthor(author.get());
            routeRepository.save(route);
            return true;
        }
        return false;
    }
}
