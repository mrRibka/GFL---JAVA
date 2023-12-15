package com.example.busstation.repository;

import com.example.busstation.data.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface RouteRepository  extends JpaRepository<Route, Long> {

    @Query("SELECT DISTINCT r FROM Route r " +
            "INNER JOIN r.stops s1 " +
            "INNER JOIN r.stops s2 " +
            "WHERE s1.city.name = :fromCity " +
            "AND s2.city.name = :toCity " +
            "AND s1.time < s2.time " +
            "AND r.id = s1.route.id " +
            "AND r.id = s2.route.id " +
            "AND s1.time >= :currentDateTime")
    List<Route> findRoutesByDirection(
            @Param("fromCity") String fromCity,
            @Param("toCity") String toCity,
            @Param("currentDateTime") LocalDateTime currentDateTime);
    Route findById(long id);
    List<Route> findAllByName(String name);
}