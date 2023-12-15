package com.example.busstation.service;

import com.example.busstation.repository.CityRepository;
import com.example.busstation.repository.RouteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CityService {
    private final CityRepository cityRepository;
}
