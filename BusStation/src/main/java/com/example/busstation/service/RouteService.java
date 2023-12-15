package com.example.busstation.service;

import com.example.busstation.data.Route;
import com.example.busstation.dto.RouteDto;
import com.example.busstation.mapper.RouteMapper;
import com.example.busstation.repository.RouteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RouteService {
    private final RouteRepository routeRepository;
    private final RouteMapper routeMapper;

    public List<RouteDto> getRoutesDTOByDirection(String fromCity, String toCity){
        return routeRepository.findRoutesByDirection(fromCity, toCity, LocalDateTime.now()).stream()
                .filter(route -> route.getPassengerCount() < route.getBusType().getMaxCapacity())
                .map(route -> routeMapper.routeDto(route, fromCity, toCity))
                .toList();
    }

    public RouteDto getRouteDTOByIdAndDirection(long id, String fromCity, String toCity){
        return routeMapper.routeDto(routeRepository.findById(id), fromCity, toCity);
    }

    public RouteDto getRouteDTOById(long id){
        return routeMapper.routeDto( routeRepository.findById(id));
    }
    public Route getRouteById(long id){
        return routeRepository.findById(id);
    }
    public void save(Route route){
         routeRepository.save(route);
    }

    public List<Route> getAll(){
        return routeRepository.findAll();
    }

    public List<Route> getAllByName(String name){
        return routeRepository.findAllByName(name);
    }

    public List<RouteDto> getSortedRoutesDTOByDirection(String fromCity, String toCity, String sort) {
        List<Route> routes = routeRepository.findRoutesByDirection(fromCity, toCity, LocalDateTime.now());

        List<Route> filteredRoutes = routes.stream()
                .filter(route -> route.getPassengerCount() < route.getBusType().getMaxCapacity())
                .collect(Collectors.toList());

        if (sort.equals("cost")) {
            filteredRoutes.sort(Comparator.comparing(Route::getCost));
        } else if (sort.equals("date")){
            filteredRoutes.sort(Comparator.comparing(route -> route.getStops().get(0).getTime()));
        } else {
            throw new RuntimeException("Sort type has not been recognized.");
        }

        return filteredRoutes.stream()
                .map(route -> routeMapper.routeDto(route, fromCity, toCity))
                .collect(Collectors.toList());
    }
}
