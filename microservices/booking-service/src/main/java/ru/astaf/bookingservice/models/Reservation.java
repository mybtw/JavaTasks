package ru.astaf.bookingservice.models;

import io.hypersistence.utils.hibernate.type.range.PostgreSQLRangeType;
import io.hypersistence.utils.hibernate.type.range.Range;
import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.Type;

import java.time.ZonedDateTime;

@Entity
@Table(name = "reservations")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotEmpty
        @Column(name = "room_id")
        private String roomId;

        @NotEmpty
        @Column(name = "preferred_username")
        private String preferredUsername;


        @Type(PostgreSQLRangeType.class)
        @Column(
                name = "booking_range",
                columnDefinition = "tstzrange"
        )
        private Range<ZonedDateTime> bookingRange;

        public Reservation(String roomId, String preferredUsername, Range<ZonedDateTime> bookingRange) {
                this.roomId = roomId;
                this.preferredUsername = preferredUsername;
                this.bookingRange = bookingRange;
        }
}
