package ru.astaf.bookingservice.repositories;

import io.hypersistence.utils.hibernate.type.range.Range;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.astaf.bookingservice.dto.RoomReservation;
import ru.astaf.bookingservice.models.Reservation;

import java.time.ZonedDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query(value = "SELECT EXISTS (" +
            "SELECT 1 FROM reservations " +
            "WHERE room_id = :roomId " +
            "AND booking_range && pg_catalog.tstzrange(:start, :end, '[]')" +
            ")", nativeQuery = true)
    boolean existsOverlappingReservations(@Param("roomId") String roomId,
                                          @Param("start") ZonedDateTime start,
                                          @Param("end") ZonedDateTime end);

    @Query(value = "SELECT room_id AS roomId, booking_range AS startTime " +
            "FROM reservations " +
            "WHERE booking_range && pg_catalog.tstzrange(NOW(), 'infinity') " +
            "AND preferred_username = :preferredUsername",
            nativeQuery = true)
    List<Object[]> findActiveAndUpcomingRoomIds(@Param("preferredUsername") String preferredUsername);


    @Transactional
    @Modifying
    @Query("delete from Reservation r where r.roomId = :roomId and r.preferredUsername = :preferredUsername and r.bookingRange = :bookingRange")
    int deleteByRoomIdAndPreferredUsernameAndBookingRange(String roomId, String preferredUsername, Range<ZonedDateTime> bookingRange);
}
