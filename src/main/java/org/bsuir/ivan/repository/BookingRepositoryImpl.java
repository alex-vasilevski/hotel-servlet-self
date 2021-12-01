package org.bsuir.ivan.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.bsuir.ivan.entity.BookingEntity;
import org.bsuir.ivan.infrostructure.BasicConnectionPool;
import org.bsuir.ivan.infrostructure.ConnectionPool;
import org.bsuir.ivan.infrostructure.WebApplicationContext;

import lombok.SneakyThrows;

public class BookingRepositoryImpl implements BookingRepository{

    private final PreparedStatementTemplateFactory templateFactory = WebApplicationContext.getBean(PreparedStatementTemplateFactory.class);
    private final ConnectionPool connectionPool = WebApplicationContext.getBean(BasicConnectionPool.class);

    @SneakyThrows
    @Override
    public void save(BookingEntity bookingEntity) {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            String preparedStatementTemplate = templateFactory.getPreparedStatement(PreparedStatementTemplate.BOOKING_SAVE);
            PreparedStatement preparedStatement = connection.prepareStatement(preparedStatementTemplate);
            preparedStatement.setObject(1, bookingEntity.getBookingId());
            preparedStatement.setObject(2, bookingEntity.getRoomNumber());
            preparedStatement.setObject(3, bookingEntity.getUserId());

            if(!preparedStatement.execute()){
                connection.rollback();
            }
            else {
                connection.commit();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if(connection != null){
                connectionPool.releaseConnection(connection);
            }
        }
    }

    @SneakyThrows
    @Override
    public Optional<BookingEntity> findById(Long id) {
        BookingEntity bookingEntity = null;
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            String sql = templateFactory.getPreparedStatement(PreparedStatementTemplate.BOOKING_FIND_BY_ID);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, id);

            ResultSet resultSet = preparedStatement.getResultSet();

            Integer room = resultSet.getInt("2");
            Long userId = resultSet.getLong("3");

            return Optional.of(new BookingEntity(id, room, userId));

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if(connection != null){
                connectionPool.releaseConnection(connection);
            }
        }
        return Optional.empty();
    }

    @SneakyThrows
    @Override
    public List<BookingEntity> findByParams(BookingEntity example) {
        Connection connection = null;
        BookingEntity bookingEntity = null;
        try {
            connection = connectionPool.getConnection();
            String preparedStatementTemplate = templateFactory.getPreparedStatement(PreparedStatementTemplate.BOOKING_FIND_BY_PARAMS);
            PreparedStatement preparedStatement = connection.prepareStatement(preparedStatementTemplate);
            preparedStatement.setObject(1, example.getRoomNumber());
            preparedStatement.setObject(2, example.getUserId());

            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.setFetchSize(1);

            List<BookingEntity> bookingEntities = new ArrayList<>();
            while (resultSet.next()){
                Long id = resultSet.getLong(1);
                Integer roomAmount = resultSet.getInt(2);
                Long userId = resultSet.getLong(3);

                bookingEntities.add(new BookingEntity(id, roomAmount, userId));
            }

            return bookingEntities;
        }
        catch (SQLException e ) {
            e.printStackTrace();
        }
        finally {
            if(connection != null){
                connectionPool.releaseConnection(connection);
            }
        }
        return Collections.emptyList();
    }

    @SneakyThrows
    @Override
    public Optional<BookingEntity> update(Long id, BookingEntity source) {
        Connection connection = null;
        BookingEntity bookingEntity = null;
        try{
            connection = connectionPool.getConnection();
            String preparedStatementTemplate = templateFactory.getPreparedStatement(PreparedStatementTemplate.BOOKING_UPDATE);
            PreparedStatement preparedStatement = connection.prepareStatement(preparedStatementTemplate);
            preparedStatement.setObject(1, source.getRoomNumber());
            preparedStatement.setObject(2, source.getUserId());
            preparedStatement.setObject(3, id);

            preparedStatement.executeUpdate();
            connection.commit();

            source.setBookingId(id);
            return Optional.of(source);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if(connection != null){
                connectionPool.releaseConnection(connection);
            }
        }

        return Optional.empty();
    }

    @SneakyThrows
    @Override
    public void deleteById(Long id) {
        Connection connection = null;
        try{
            connection = connectionPool.getConnection();
            String preparedStatementTemplate = templateFactory.getPreparedStatement(PreparedStatementTemplate.BOOKING_DELETE);

            PreparedStatement preparedStatement = connection.prepareStatement(preparedStatementTemplate);
            preparedStatement.setObject(1, id);

            if(!preparedStatement.execute()){
                connection.rollback();
            }
            else{
                connection.commit();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if(connection != null){
                connectionPool.releaseConnection(connection);
            }
        }
    }
}
