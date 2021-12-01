package org.bsuir.ivan.servlets;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bsuir.ivan.controller.BookingController;
import org.bsuir.ivan.controller.Controller;
import org.bsuir.ivan.dao.Booking;
import org.bsuir.ivan.dao.BookingSearchRequest;
import org.bsuir.ivan.dao.BookingUpdateRequest;
import org.bsuir.ivan.dao.RequestFactory;
import org.bsuir.ivan.infrostructure.BasicIdFactory;
import org.bsuir.ivan.infrostructure.IdFactory;
import org.bsuir.ivan.infrostructure.JsonFactory;
import org.bsuir.ivan.infrostructure.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import sun.security.util.IOUtils;

@WebServlet(name = "Booking", value = "/booking")
public class BookingServlet extends HttpServlet {

    private final Controller<Long, Booking, BookingSearchRequest, BookingUpdateRequest> bookingController = WebApplicationContext.getBean(BookingController.class);
    private final IdFactory idFactory = WebApplicationContext.getBean(BasicIdFactory.class);
    private final RequestFactory searchRequestFactory = WebApplicationContext.getBean(RequestFactory.class);
    private final JsonFactory jsonFactory = WebApplicationContext.getBean(JsonFactory.class);
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String queryString = req.getQueryString();

        if (queryString.isEmpty()) {
            Long id = idFactory.createId(req.getPathInfo());
            Booking booking = bookingController.findById(id);
            req.setAttribute("booking", booking);
        } else {
            BookingSearchRequest bookingSearchRequest = searchRequestFactory.create(queryString, BookingSearchRequest.class);
            List<Booking> bookings = bookingController.findByParams(bookingSearchRequest);
            int counter = 0;
            for (Booking booking : bookings) {
                req.setAttribute("booking" + counter, booking);
                counter += 1;
            }
        }

        req.getRequestDispatcher("bookings.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jsonString = jsonFactory.create(req.getInputStream());
        Booking booking = mapper.readValue(jsonString, Booking.class);
        bookingController.save(booking);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jsonString = jsonFactory.create(req.getInputStream());
        Long id = idFactory.createId(req.getPathInfo());
        BookingUpdateRequest bookingUpdateRequest = mapper.readValue(jsonString, BookingUpdateRequest.class);
        Booking update = bookingController.update(id, bookingUpdateRequest);
        req.setAttribute("updated", update);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = idFactory.createId(req.getPathInfo());
        bookingController.deleteById(id);
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
