package com.example.busstation.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id", nullable = false)
    private Long id;

    @Column(name = "First_Nname", length = 50, nullable = false)
    private String firstName;

    @Column(name = "Last_Name", length = 50, nullable = false)
    private String lastName;

    @Column(name = "From_City", length = 50, nullable = false)
    private String fromCity;

    @Column(name = "To_City", length = 50, nullable = false)
    private String toCity;

    @Column(name = "Cost", precision = 5, scale = 2, nullable = false)
    private BigDecimal cost;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    @Column(name = "Is_Returned")
    private boolean isReturned = false;
}
