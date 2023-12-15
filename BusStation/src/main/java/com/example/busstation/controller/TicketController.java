package com.example.busstation.controller;

import com.example.busstation.data.Route;
import com.example.busstation.data.Ticket;
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
public class TicketController {
    private final TicketService ticketService;
    private final RouteService routeService;
    @GetMapping("/tickets")
    public String showSearchForm(Model model) {

        model.addAttribute("tickets", ticketService.getAll());
        model.addAttribute("lastName", "");
        model.addAttribute("firstName", "");
        model.addAttribute("routeName", "");
        return "search_ticket";
    }
    @PostMapping("/tickets")
    public String showSearchResults(@RequestParam("lastName") String lastName,
                                    @RequestParam("firstName") String firstName,
                                    @RequestParam("routeName") String routeName,
                                    Model model) {

        List<Ticket> tickets;
        if (routeName.isEmpty()){
            tickets = ticketService.getAllByLastName(lastName, firstName);
        } else {
            tickets = ticketService.getAllByLastNameAndRouteName(lastName, firstName, routeName);
        }

        model.addAttribute("tickets", tickets);
        model.addAttribute("lastName", lastName);
        model.addAttribute("firstName", firstName);
        model.addAttribute("routeName", routeName);
        return "search_ticket";
    }
    @PostMapping("/tickets/return/{id}")
    public String returnTicket(@PathVariable("id")  Long id,
                               @RequestParam("lastName") String lastName,
                               @RequestParam("firstName") String firstName,
                               @RequestParam("routeName") String routeName,
                               Model model) {

        Ticket ticket = ticketService.getById(id);
        Route route = ticket.getRoute();
        route.setPassengerCount(route.getPassengerCount() - 1);
        ticket.setReturned(true);
        ticketService.save(ticket);
        routeService.save(route);

        return showSearchResults(lastName,firstName, routeName,  model);
    }
}
