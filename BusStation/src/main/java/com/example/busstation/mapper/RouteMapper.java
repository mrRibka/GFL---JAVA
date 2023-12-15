package com.example.busstation.mapper;

import com.example.busstation.data.Route;
import com.example.busstation.data.Stop;
import com.example.busstation.dto.RouteDto;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;

@ApplicationScope
@Service
public class RouteMapper {
    public RouteDto routeDto(Route r, String fromCity, String toCity) {

        Stop firstStop = r.getStops().stream().filter(s -> s.getCity().getName().equals(fromCity)).findFirst().get();
        Stop lastStop = r.getStops().stream().filter(s -> s.getCity().getName().equals(toCity)).findFirst().get();

        List<Stop> stopsBetweenCities  = r.getStops().stream()
                .filter(stop -> !stop.getTime().isBefore(firstStop.getTime()) && !stop.getTime().isAfter(lastStop.getTime()))
                .sorted(Comparator.comparing(Stop::getTime))
                .toList();

        Duration travelTime = Duration.between(stopsBetweenCities.get(0).getTime(), stopsBetweenCities.get(stopsBetweenCities.size() - 1).getTime());

        return new RouteDto(r.getId(), r.getName(), stopsBetweenCities, r.getBusType(), travelTime, r.getCost(), r.getPassengerCount());
    }
    public RouteDto routeDto(Route r) {
        List<Stop> stopsBetweenCities  = r.getStops().stream()
                .sorted(Comparator.comparing(Stop::getTime))
                .toList();

        Duration travelTime = Duration.between(stopsBetweenCities.get(0).getTime(), stopsBetweenCities.get(stopsBetweenCities.size() - 1).getTime());

        return new RouteDto(r.getId(), r.getName(), stopsBetweenCities, r.getBusType(), travelTime, r.getCost(), r.getPassengerCount());
    }
}
