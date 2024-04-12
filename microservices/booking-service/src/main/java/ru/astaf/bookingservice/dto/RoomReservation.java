package ru.astaf.bookingservice.dto;

import io.hypersistence.utils.hibernate.type.range.Range;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomReservation {
    private String roomId;
    private String dateTime;
}
