package org.bsuir.ivan.service;

import java.util.List;

import org.bsuir.ivan.dao.Booking;
import org.bsuir.ivan.dao.BookingSearchRequest;
import org.bsuir.ivan.dao.BookingUpdateRequest;

public interface BookingService {

    void save(Booking booking);

    Booking findById(Long id);

    List<Booking> findByParams(BookingSearchRequest searchRequest);

    Booking update(Long id, BookingUpdateRequest updateRequest);

    void deleteById(Long id);
}
