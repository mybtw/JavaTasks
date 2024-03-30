package ru.astaf.bookingservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.astaf.bookingservice.dto.ReservationRequest;
import ru.astaf.bookingservice.dto.ReservationResponse;
import ru.astaf.bookingservice.services.ReservationService;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/reserve")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ReservationResponse> reserve(@RequestBody ReservationRequest reservationRequest) {
        Long roomId = reservationService.createReservation(reservationRequest);
        return ResponseEntity.ok(new ReservationResponse(roomId));
    }
}
