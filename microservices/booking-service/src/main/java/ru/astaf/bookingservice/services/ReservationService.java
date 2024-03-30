package ru.astaf.bookingservice.services;

import io.hypersistence.utils.hibernate.type.range.Range;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.astaf.bookingservice.dto.ReservationRequest;
import ru.astaf.bookingservice.models.Reservation;
import ru.astaf.bookingservice.repositories.ReservationRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationService {
    private final ReservationRepository reservationRepository;
    public Long createReservation(ReservationRequest reservationRequest) {
        Reservation reservation = new Reservation(reservationRequest.getRoomId(), Range.closed(reservationRequest.getStart(), reservationRequest.getEnd()));
        Reservation savedReservation = reservationRepository.save(reservation);
        log.info("reservation with id = {} was created", savedReservation.getId());
        return savedReservation.getId();
    }
}
