package ru.astaf.bookingservice;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.astaf.bookingservice.dto.ReservationRequest;
import ru.astaf.bookingservice.repositories.ReservationRepository;
import ru.astaf.bookingservice.services.ReservationService;

import java.time.ZonedDateTime;

@SpringBootTest
@Testcontainers
class BookingServiceApplicationTests {

    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:12")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationRepository reservationRepository;

    @BeforeAll
    static void setup() {
        // Конфигурация для использования базы данных в контейнере
        System.setProperty("spring.datasource.url", postgreSQLContainer.getJdbcUrl());
        System.setProperty("spring.datasource.username", postgreSQLContainer.getUsername());
        System.setProperty("spring.datasource.password", postgreSQLContainer.getPassword());
    }

    @AfterAll
    static void tearDown() {
        postgreSQLContainer.stop();
    }

    @Test
    public void testCreateReservation() {
        ReservationRequest request = new ReservationRequest("1", ZonedDateTime.now(), ZonedDateTime.now().plusHours(2));
        Long reservationId = reservationService.createReservation(request, "user1");
        Assertions.assertNotNull(reservationId);
    }

    @Test
    public void testDeleteReservation() {
        Long roomId = 1L;
        String preferredUsername = "user1";
        String duration = "[\"2024-04-11 20:58:40.73+03\",\"2024-04-11 22:58:40.73+03\"]";
        reservationService.delete("1", duration, preferredUsername);
        boolean exists = reservationRepository.existsById(roomId);
        Assertions.assertFalse(exists);
    }

    @Test
    public void testCannotCreateOverlappingReservations() {
        ZonedDateTime now = ZonedDateTime.now();
        // Создаем начальное бронирование
        ReservationRequest initialRequest = new ReservationRequest("1", now, now.plusHours(2));
        Long initialReservationId = reservationService.createReservation(initialRequest, "user1");
        Assertions.assertNotNull(initialReservationId);

        // Попытка создать пересекающееся бронирование
        ReservationRequest overlappingRequest = new ReservationRequest("1", now.plusHours(1), now.plusHours(3));
        Assertions.assertThrows(Exception.class, () -> {
            reservationService.createReservation(overlappingRequest, "user1");
        });
    }

}
