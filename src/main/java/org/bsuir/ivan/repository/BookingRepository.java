package org.bsuir.ivan.repository;

import java.util.List;
import java.util.Optional;

import org.bsuir.ivan.entity.BookingEntity;

public interface BookingRepository {

    void save(BookingEntity bookingEntity);
    Optional<BookingEntity> findById(Long id);
    List<BookingEntity> findByParams(BookingEntity entityParams);
    Optional<BookingEntity> update(Long id, BookingEntity source);
    void deleteById(Long id);

}
