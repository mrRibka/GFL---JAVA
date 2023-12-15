package com.example.busstation.controller;

import com.example.busstation.data.Route;
import com.example.busstation.data.Ticket;
import com.example.busstation.dto.RouteDto;
import com.example.busstation.service.RouteService;
import com.example.busstation.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class BoardingController {
    private final RouteService routeService;
    private final TicketService ticketService;
    @GetMapping("/boarding")
    public String showSearchForm(Model model) {

        model.addAttribute("tickets", routeService.getAll());
        model.addAttribute("routeName", "");
        return "search_boarding";
    }
    @PostMapping("/boarding")
    public String showSearchResults(@RequestParam("routeName") String routeName,
                                    Model model) {
        List<Route> routes = routeService.getAllByName(routeName);

        model.addAttribute("routes", routes);
        model.addAttribute("routeName", routeName);
        return "search_boarding";
    }
    @GetMapping("/boarding/{id}")
    public String showRouteInfo(@PathVariable Long id,
                                Model model) {
        RouteDto route = routeService.getRouteDTOById(id);
        List<Ticket> tickets = ticketService.getValidTicketsByRouteId(id);
        model.addAttribute("route", route);
        model.addAttribute("tickets", tickets);
        return "boarding_info";
    }
    @GetMapping("/boarding/{id}/generateFile")
    public String generateFile(@PathVariable Long id,
                                Model model) {
        ticketService.createTicketFileForRoute(id);
        return showRouteInfo(id, model);
    }
}
