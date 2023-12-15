package com.example.busstation.data;

import lombok.Getter;

@Getter
public enum BusType {
    SMALL(20),
    MEDIUM(40),
    LARGE(60);

    private final int maxCapacity;

    BusType(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

}
