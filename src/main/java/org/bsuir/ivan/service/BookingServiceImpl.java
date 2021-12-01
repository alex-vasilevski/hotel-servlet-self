package org.bsuir.ivan.service;

import java.util.List;
import java.util.stream.Collectors;

import org.bsuir.ivan.dao.Booking;
import org.bsuir.ivan.dao.BookingSearchRequest;
import org.bsuir.ivan.dao.BookingUpdateRequest;
import org.bsuir.ivan.entity.BookingEntity;
import org.bsuir.ivan.infrostructure.WebApplicationContext;
import org.bsuir.ivan.repository.BookingRepository;
import org.bsuir.ivan.repository.BookingRepositoryImpl;
import org.bsuir.ivan.transformers.BasicTransformer;
import org.bsuir.ivan.transformers.Transformer;

import lombok.SneakyThrows;

public class BookingServiceImpl implements BookingService{

    private final BookingRepository bookingRepository = WebApplicationContext.getBean(BookingRepositoryImpl.class);
    private final Transformer transformer = WebApplicationContext.getBean(BasicTransformer.class);

    @Override
    public void save(Booking booking) {
        BookingEntity entity = (BookingEntity) transformer.transform(booking, BookingEntity.class);
        bookingRepository.save(entity);
    }

    @SneakyThrows
    @Override
    public Booking findById(Long id) {
        BookingEntity entity = bookingRepository.findById(id).orElseThrow(BookEntityNotFoundException::new);
        return (Booking) transformer.transform(entity, Booking.class);
    }

    @Override
    public List<Booking> findByParams(BookingSearchRequest searchRequest) {
        BookingEntity example = (BookingEntity) transformer.transform(searchRequest, BookingEntity.class);
        List<BookingEntity> entities = bookingRepository.findByParams(example);
        return entities.stream()
                .map(entity -> (Booking) transformer.transform(entity, Booking.class))
                .collect(Collectors.toList());
    }

    @SneakyThrows
    @Override
    public Booking update(Long id, BookingUpdateRequest updateRequest) {
        BookingEntity source = (BookingEntity) transformer.transform(updateRequest, BookingEntity.class);
        BookingEntity update = bookingRepository.update(id, source).orElseThrow(BookEntityNotFoundException::new);
        return (Booking) transformer.transform(update, Booking.class);
    }

    @Override
    public void deleteById(Long id) {
        bookingRepository.deleteById(id);
    }
}
