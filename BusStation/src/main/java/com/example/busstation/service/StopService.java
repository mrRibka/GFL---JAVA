package com.example.busstation.service;

import com.example.busstation.data.Stop;
import com.example.busstation.repository.RouteRepository;
import com.example.busstation.repository.StopRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StopService {
    private final StopRepository stopRepository;
}
