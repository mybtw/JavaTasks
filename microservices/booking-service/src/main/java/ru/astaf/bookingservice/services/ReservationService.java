package ru.astaf.bookingservice.services;

import io.hypersistence.utils.hibernate.type.range.Range;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.astaf.bookingservice.dto.ReservationRequest;
import ru.astaf.bookingservice.dto.RoomReservation;
import ru.astaf.bookingservice.dto.UserReservationResponse;
import ru.astaf.bookingservice.models.Reservation;
import ru.astaf.bookingservice.repositories.ReservationRepository;
import org.postgresql.util.PGobject;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationService {
    private final ReservationRepository reservationRepository;
    public Long createReservation(ReservationRequest reservationRequest, String preferredUsername) {
        Reservation reservation = new Reservation(reservationRequest.getRoomId(), preferredUsername, Range.closed(reservationRequest.getStart(), reservationRequest.getEnd()));
        Reservation savedReservation = reservationRepository.save(reservation);
        log.info("reservation with id = {} was created", savedReservation.getId());
        return savedReservation.getId();
    }

    public boolean checkForOverlappingReservations(String roomId, ZonedDateTime start, ZonedDateTime end) {
        return !reservationRepository.existsOverlappingReservations(roomId, start, end);
    }

    public List<String> filterFreeRooms(List<String> roomIds, ZonedDateTime start, ZonedDateTime end) {
        return roomIds.stream().filter(id -> checkForOverlappingReservations(id, start, end)).toList();
    }

    public UserReservationResponse findActiveAndUpcomingRoomIds(String preferredUsername) {
        List<Object[]> results = reservationRepository.findActiveAndUpcomingRoomIds(preferredUsername);
        List<RoomReservation> reservations = results.stream()
                .map(result -> {
                    String roomId = (String) result[0];
                    String dateTime = pgObjectToString((PGobject) result[1]);
                    return new RoomReservation(roomId, dateTime);
                })
                .collect(Collectors.toList());
        return new UserReservationResponse(reservations);
    }


    public static String pgObjectToString(PGobject object){
        if(null==object) return null;
        String s = object.getValue();
        return s;
    }

    public static Range<ZonedDateTime> parseDateRange(String jsonRange) {
        String cleanJson = jsonRange.replaceAll("[\\[\\]\"]", "");
        String[] dates = cleanJson.split(",");

        // Парсинг дат
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSX");

        ZonedDateTime start = ZonedDateTime.parse(dates[0].replace(" ", "T").trim());
        ZonedDateTime end = ZonedDateTime.parse(dates[1].replace(" ", "T").trim());

        return Range.closed(start, end);
    }

    public void delete(String roomId, String duration, String preferredUsername) {
//        ZonedDateTime start = ZonedDateTime.parse("2024-04-01T10:00:00+01:00");
//        ZonedDateTime end = ZonedDateTime.parse("2024-04-10T10:00:00+01:00");
//        //["2024-04-11 20:58:40.73+03","2024-04-11 22:58:40.73+03"]
//        Range<ZonedDateTime> bookingRange = Range.closed(start, end);
//        String[] dateStrings = input.replace("[", "").replace("]", "").split(",");
//
//        // Обработка каждой строки, удаление кавычек и пробелов
//        ZonedDateTime[] dates = new ZonedDateTime[dateStrings.length];
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSX");
//
//        for (int i = 0; i < dateStrings.length; i++) {
//            String cleanedDate = dateStrings[i].trim().replace("\"", "");
//            String isoDate = cleanedDate.replace(" ", "T");
//            dates[i] = ZonedDateTime.parse(isoDate, formatter);
//        }
        System.out.println("===============");
        System.out.println(duration);
        System.out.println(roomId);
        System.out.println(preferredUsername);
        System.out.println("===============");
        var dateRange = parseDateRange(duration);
        System.out.println("ПОИСК ДАТЫ " + dateRange);
        reservationRepository.deleteByRoomIdAndPreferredUsernameAndBookingRange(roomId, preferredUsername, dateRange);
    }
}
