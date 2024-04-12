package ru.astaf.roommanagementservice.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.astaf.roommanagementservice.dto.OfficeAddress;
import ru.astaf.roommanagementservice.dto.RoomRequest;
import ru.astaf.roommanagementservice.dto.RoomResponse;
import ru.astaf.roommanagementservice.dto.RoomSearchCriteria;
import ru.astaf.roommanagementservice.models.Room;
import ru.astaf.roommanagementservice.sevices.RoomService;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomsController {

    private final RoomService roomService;

    private final RestTemplate restTemplate;
 // @RequestHeader("X-Preferred-Username") String preferredUsername
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RoomResponse> getAllRooms() {
        return roomService.getAllRooms();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createRoom(@RequestBody RoomRequest roomRequest) {
        roomService.createRoom(roomRequest);
    }

    @GetMapping("/{roomId}")
    @ResponseStatus(HttpStatus.OK)
    public RoomResponse getRoom(@PathVariable String roomId) {
        return roomService.getRoomById(roomId);
    }

    @PutMapping("/{roomId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateRoom(@PathVariable String roomId, @RequestBody RoomRequest roomRequest) {
        roomService.updateRoom(roomId, roomRequest);
    }

    @DeleteMapping("/{roomId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRoom(@PathVariable String roomId) {
        roomService.deleteRoom(roomId);
    }

    @PostMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<RoomResponse> searchRooms(@RequestBody RoomSearchCriteria criteria, HttpServletRequest request) {
        System.out.println(criteria.toString());
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        URI uri = UriComponentsBuilder
                .fromHttpUrl("http://localhost:8080/api/reservations/check-availability")
                .queryParam("start", criteria.getStart().toString())
                .queryParam("end", criteria.getEnd().toString())
                .build().toUri();
        var allRooms = roomService.searchRooms(criteria);
        List<String> roomIds = allRooms.stream()
                .map(RoomResponse::getId)
                .collect(Collectors.toList());
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, authorizationHeader);
        HttpEntity<List<String>> requestEntity = new HttpEntity<>(roomIds, headers);
        ResponseEntity<List<String>> response = restTemplate.exchange(
                uri,
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<>() {
                }
        );

        var responseBody = response.getBody();

        var availableRoomIds = new HashSet<>();

        if (responseBody != null) {
            availableRoomIds.addAll(responseBody);
        }

        // Проверка на пустой список доступных комнат
        if (availableRoomIds.isEmpty()) {
            return new ArrayList<>();
        }

        return allRooms.stream()
                .filter(room -> availableRoomIds.contains(room.getId()))
                .collect(Collectors.toList());
    }

    @GetMapping("/addresses")
    @ResponseStatus(HttpStatus.OK)
    public List<OfficeAddress> searchRooms() {
        return roomService.getAddresses();
    }

    @PostMapping("user-rooms")
    public List<RoomResponse> getRoomsByIdList(@RequestBody List<String> idList) {
        System.out.println("ИЩЕМ " + idList.toString());
        return roomService.findAllById(idList);
    }
}
