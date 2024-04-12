package ru.astaf.bookingservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.astaf.bookingservice.dto.ReservationRequest;
import ru.astaf.bookingservice.dto.ReservationResponse;
import ru.astaf.bookingservice.dto.UserReservationResponse;
import ru.astaf.bookingservice.services.ReservationService;

import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/reserve")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ReservationResponse> reserve(@RequestBody ReservationRequest reservationRequest, @RequestHeader("X-Preferred-Username") String preferredUsername) {
        System.out.println(reservationRequest.toString());
        Long roomId = reservationService.createReservation(reservationRequest, preferredUsername);
        return ResponseEntity.ok(new ReservationResponse(roomId));
    }

    @PostMapping("/check-availability")
    public ResponseEntity<List<String>> checkRoomsAvailability(@RequestBody List<String> roomIds,
                                                             @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime start,
                                                             @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime end) {
        return ResponseEntity.ok(reservationService.filterFreeRooms(roomIds, start, end));
    }

    @DeleteMapping()
    @ResponseBody
    public void deleteReservation(@RequestParam("room_id") String roomId, @RequestParam("duration") String duration, @RequestHeader("X-Preferred-Username") String preferredUsername) {
        reservationService.delete(roomId, duration, preferredUsername);
    }

    @GetMapping
    public UserReservationResponse getUserReservations(@RequestHeader("X-Preferred-Username") String preferredUsername) {
        System.out.println(preferredUsername);
        return reservationService.findActiveAndUpcomingRoomIds(preferredUsername);
    }
       /*ZonedDateTime start = ZonedDateTime.parse("2024-04-01T10:00:00+01:00");
		ZonedDateTime end = ZonedDateTime.parse("2024-04-10T10:00:00+01:00");
		Range<ZonedDateTime> bookingRange = Range.closed(start, end);
		System.out.println(bookingRange.asString());*/
}
