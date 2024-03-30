package ru.astaf.bookingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservationRequest {
    private String roomId;
    private ZonedDateTime start;
    private ZonedDateTime end;
}
