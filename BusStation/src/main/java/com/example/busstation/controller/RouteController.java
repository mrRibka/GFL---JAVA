package com.example.busstation.controller;

import com.example.busstation.data.Route;
import com.example.busstation.data.Ticket;
import com.example.busstation.dto.RouteDto;
import com.example.busstation.repository.RouteRepository;
import com.example.busstation.repository.TicketRepository;
import com.example.busstation.service.RouteService;
import com.example.busstation.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@AllArgsConstructor
public class RouteController {
    private final RouteService routeService;
    private final TicketService ticketService;

    @GetMapping("/")
    public String showMainPage(){
        return "main_page";
    }

    @GetMapping("/search")
    public String showSearchForm(@RequestParam(value = "toCity", required = false) String toCity,
                                 @RequestParam(value = "fromCity", required = false) String fromCity,
                                 @RequestParam(value = "sort", required = false) String sort,
                                Model model) {
        if (toCity != null && fromCity != null){
            List<RouteDto> routes;
            if (sort == null) {
                routes = routeService.getRoutesDTOByDirection(fromCity, toCity);
            } else{
                routes = routeService.getSortedRoutesDTOByDirection(fromCity, toCity, sort);
                System.out.println(routes);
            }
            model.addAttribute("routes", routes);
            model.addAttribute("toCity", toCity);
            model.addAttribute("fromCity", fromCity);
        }else {
            model.addAttribute("toCity", "");
            model.addAttribute("fromCity", "");
        }

        return "search_route";
    }


    @PostMapping("/search")
    public String searchRoutes(@RequestParam("toCity") String toCity,
                               @RequestParam("fromCity") String fromCity,
                               @RequestParam(value = "sort", required = false) String sort,
                               Model model) {

        List<RouteDto> routes;
        routes = routeService.getRoutesDTOByDirection(fromCity, toCity);


        model.addAttribute("routes", routes);
        model.addAttribute("toCity", toCity);
        model.addAttribute("fromCity", fromCity);

        return "search_route";
    }

    @GetMapping("/search/route/{id}")
    public String getRouteDetails(@PathVariable Long id,
                                  @RequestParam("toCity") String toCity,
                                  @RequestParam("fromCity") String fromCity,
                                  Model model) {
        model.addAttribute("route", routeService.getRouteDTOByIdAndDirection(id, fromCity, toCity));
        model.addAttribute("numberOfTickets", 1);
        model.addAttribute("fromCity", fromCity);
        model.addAttribute("toCity", toCity);

        return "route_info";
    }

    @PostMapping("/search/route/{id}/buy")
    public String buyTicket(@PathVariable Long id,
                            @ModelAttribute Ticket ticket,
                            RedirectAttributes redirectAttributes) {
        Route route = routeService.getRouteById(id);
        route.setPassengerCount(route.getPassengerCount() + 1);

        if (route.getPassengerCount() > route.getBusType().getMaxCapacity()) {
            redirectAttributes.addAttribute("errorMessage", "No tickets on this route!");
            redirectAttributes.addAttribute("fromCity", ticket.getFromCity());
            redirectAttributes.addAttribute("toCity", ticket.getToCity());

            return "redirect:/search/route/" + id;
        }

        routeService.save(route);
        ticketService.save(ticket);

        redirectAttributes.addAttribute("successMessage", "Tickets purchased successfully!");
        redirectAttributes.addAttribute("fromCity", ticket.getFromCity());
        redirectAttributes.addAttribute("toCity", ticket.getToCity());

        return "redirect:/search/route/" + id;
    }
}
