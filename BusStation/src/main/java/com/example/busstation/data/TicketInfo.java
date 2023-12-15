package com.example.busstation.data;


import org.springframework.beans.factory.annotation.Value;

public interface TicketInfo {
    @Value("#{target.firstName + ' ' + target.lastName}")
    String getFullName();
    @Value("#{target.fromCity + ' ' + target.toCity}")
    String getDirection();
}
