package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.model.dto.MostCommentedRouteDto;
import bg.softuni.pathfinder.repository.RouteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class RouteService {
    private  final RouteRepository routeRepository;
    private  final ModelMapper mapper;




    public RouteService(RouteRepository routeRepository, ModelMapper mapper) {
        this.routeRepository = routeRepository;
        this.mapper = mapper;
    }


    public MostCommentedRouteDto getMostCommentedRoute() {
        MostCommentedRouteDto mostCommentedRouteDto = mapper.map(routeRepository.findMostCommentedRoute(), MostCommentedRouteDto.class);
        return mostCommentedRouteDto;

    }
}
