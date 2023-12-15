package com.example.busstation.dto;

import com.example.busstation.data.BusType;
import com.example.busstation.data.Stop;
import lombok.Value;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;

@Value
public class RouteDto {
    Long id;
    String name;
    List<Stop> stops;
    BusType busType;
    Duration travelTime;
    BigDecimal cost;
    int passengerCount;
}
