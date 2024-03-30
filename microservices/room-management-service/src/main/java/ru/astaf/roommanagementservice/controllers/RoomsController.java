package ru.astaf.roommanagementservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.astaf.roommanagementservice.dto.RoomRequest;
import ru.astaf.roommanagementservice.dto.RoomResponse;
import ru.astaf.roommanagementservice.dto.RoomSearchCriteria;
import ru.astaf.roommanagementservice.sevices.RoomService;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomsController {

    private final RoomService roomService;

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

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<RoomResponse> searchRooms(@RequestBody RoomSearchCriteria criteria) {
        return roomService.searchRooms(criteria);
    }
}
