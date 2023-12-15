package com.example.busstation.repository;

import com.example.busstation.data.Ticket;
import com.example.busstation.data.TicketInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findAllByLastNameContainingAndFirstNameContainingAndRoute_Name(String lastName, String firstName, String routeName);
    List<Ticket> findAllByLastNameContainingAndFirstNameContaining(String lastName, String firstName);

    List<Ticket> findAllByRoute_IdAndIsReturnedFalse(Long routeId);
    List<TicketInfo> findTicketInfoByRoute_IdAndIsReturnedFalse(Long routeId);
}
