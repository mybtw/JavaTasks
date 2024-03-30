package ru.astaf.bookingservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.astaf.bookingservice.models.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
