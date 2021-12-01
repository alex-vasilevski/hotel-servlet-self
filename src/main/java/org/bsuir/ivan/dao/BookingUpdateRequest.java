package org.bsuir.ivan.dao;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class BookingUpdateRequest {
    private final Integer roomNumber;
    private final User user;
}
