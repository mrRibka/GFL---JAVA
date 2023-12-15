package com.example.busstation.service;

import com.example.busstation.data.Route;
import com.example.busstation.data.Ticket;
import com.example.busstation.data.TicketInfo;
import com.example.busstation.repository.TicketRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final RouteService routeService;
    private final ObjectMapper objectMapper;

    public void save(Ticket ticket){
        ticketRepository.save(ticket);
    }

    public List<Ticket> getAll() {
        return ticketRepository.findAll();
    }
    public List<Ticket> getAllByLastName(String lastName, String firstName) {
        return ticketRepository.findAllByLastNameContainingAndFirstNameContaining(lastName, firstName);
    }

    public List<Ticket> getAllByLastNameAndRouteName(String lastName, String firstName, String routeName) {
        return ticketRepository.findAllByLastNameContainingAndFirstNameContainingAndRoute_Name(lastName, firstName, routeName);
    }
    public Ticket getById(Long id){
        return ticketRepository.findById(id).get();
    }

    public List<Ticket> getValidTicketsByRouteId(Long id){
        return ticketRepository.findAllByRoute_IdAndIsReturnedFalse(id);
    }
    public void createTicketFileForRoute(Long id) {
        List<TicketInfo> tickets = ticketRepository.findTicketInfoByRoute_IdAndIsReturnedFalse(id);
        Route route = routeService.getRouteById(id);

        String routeName = route.getName().replaceAll("[^a-zA-Z0-9.-]", "_");
        String directoryPath = "boarding_lists";

        File directory = new File(directoryPath);
        if (!directory.exists()) {
            boolean isDirectoryCreated = directory.mkdirs();
            if (!isDirectoryCreated) {
                throw new RuntimeException("Failed to create directory");
            }
        }

        File outputFile = new File(directory, routeName + ".json");

        try {
            if (!tickets.isEmpty()) {
                objectMapper.writeValue(outputFile, tickets);
            } else {
                if (!outputFile.createNewFile()) {
                    throw new IOException("Failed to create empty file");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
