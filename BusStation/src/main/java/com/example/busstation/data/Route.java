package com.example.busstation.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Long id;

    @Column(name = "Name", length = 20, nullable = false)
    private String name;

    @Column(name = "Passenger_Count", columnDefinition = "integer default 0")
    private int passengerCount;

    @Enumerated(EnumType.STRING)
    @Column(name = "Bus_Type", nullable = false)
    private BusType busType;

    @Column(name = "Cost", precision = 5, scale = 2, nullable = false)
    private BigDecimal cost;

    @OneToMany(mappedBy = "route")
    private List<Stop> stops;

    @OneToMany(mappedBy = "route")
    private List<Ticket> tickets;
}