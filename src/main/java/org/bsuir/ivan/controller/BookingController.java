package org.bsuir.ivan.controller;

import java.util.List;

import org.bsuir.ivan.dao.Booking;
import org.bsuir.ivan.dao.BookingSearchRequest;
import org.bsuir.ivan.dao.BookingUpdateRequest;
import org.bsuir.ivan.infrostructure.WebApplicationContext;
import org.bsuir.ivan.service.BookingService;
import org.bsuir.ivan.service.BookingServiceImpl;

public class BookingController implements Controller<Long, Booking, BookingSearchRequest, BookingUpdateRequest>{

    private final BookingService service = WebApplicationContext.getBean(BookingServiceImpl.class);

    @Override
    public void save(Booking booking) {
        service.save(booking);
    }

    @Override
    public Booking findById(Long id) {
        return service.findById(id);
    }

    @Override
    public List<Booking> findByParams(BookingSearchRequest bookingSearchRequest) {
        return service.findByParams(bookingSearchRequest);
    }

    @Override
    public Booking update(Long id, BookingUpdateRequest bookingUpdateRequest) {
        return service.update(id, bookingUpdateRequest);
    }

    @Override
    public void deleteById(Long id) {
        service.deleteById(id);
    }
}
