package org.bsuir.ivan.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BookingEntity {
    private Long bookingId;
    private Integer roomNumber;
    private Long userId;
}
