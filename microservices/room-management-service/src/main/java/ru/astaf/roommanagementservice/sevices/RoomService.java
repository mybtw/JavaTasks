package ru.astaf.roommanagementservice.sevices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import ru.astaf.roommanagementservice.dto.RoomRequest;
import ru.astaf.roommanagementservice.dto.RoomResponse;
import ru.astaf.roommanagementservice.dto.RoomSearchCriteria;
import ru.astaf.roommanagementservice.models.Room;
import ru.astaf.roommanagementservice.repositories.RoomRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomService {

    private final ModelMapper modelMapper;
    private final RoomRepository roomRepository;

    public void createRoom(RoomRequest roomRequest) {
        Room room = convertFromRequestDTO(roomRequest);
        roomRepository.save(room);
        log.info("Room {} was saved", room.getId());
    }

    private Room convertFromRequestDTO(RoomRequest roomRequest) {
        return modelMapper.map(roomRequest, Room.class);
    }

    private RoomResponse convertToResponseDTO(Room room) {
        return modelMapper.map(room, RoomResponse.class);
    }

    public List<RoomResponse> getAllRooms() {
        List<Room> roomList = roomRepository.findAll();
        return roomList.stream().map(this::convertToResponseDTO).toList();
    }

    public RoomResponse getRoomById(String roomId) {
        return modelMapper.map(roomRepository.findById(roomId), RoomResponse.class);
    }

    public void updateRoom(String roomId, RoomRequest roomRequest) {
        Optional<Room> roomOptional = roomRepository.findById(roomId);
        if (roomOptional.isPresent()) {
            Room room = roomOptional.get();
            modelMapper.map(roomRequest, room);
            roomRepository.save(room);
            log.info("Комната с id = {} обновлена", room.getId());
        } else {
            throw new IllegalArgumentException("Room with ID " + roomId + " not found");
        }
    }

    public void deleteRoom(String roomId) {
        roomRepository.deleteById(roomId);
        log.info("Комната с id = {} удалена", roomId);
    }

    public List<RoomResponse> searchRooms(RoomSearchCriteria criteria) {
        Query query = new Query();

        if (criteria.getName() != null) {
            query.addCriteria(Criteria.where("name").regex(criteria.getName(), "i"));
        }
        if (criteria.getAddress() != null) {
            query.addCriteria(Criteria.where("address").regex(criteria.getAddress(), "i"));
        }
        if (criteria.getMinCapacity() != null && criteria.getMaxCapacity() != null) {
            query.addCriteria(Criteria.where("capacity").gte(criteria.getMinCapacity()).lte(criteria.getMaxCapacity()));
        } else if (criteria.getMinCapacity() != null) {
            query.addCriteria(Criteria.where("capacity").gte(criteria.getMinCapacity()));
        } else if (criteria.getMaxCapacity() != null) {
            query.addCriteria(Criteria.where("capacity").lte(criteria.getMaxCapacity()));
        }
        if (criteria.getHasMonitorOrTV() != null) {
            query.addCriteria(Criteria.where("hasMonitorOrTV").is(criteria.getHasMonitorOrTV()));
        }
        if (criteria.getHasVideoConferencing() != null) {
            query.addCriteria(Criteria.where("hasVideoConferencing").is(criteria.getHasVideoConferencing()));
        }

        List<Room> rooms = roomRepository.getRoomsBySearchCriteria(query);

        return rooms.stream().map(room -> modelMapper.map(room, RoomResponse.class)).collect(Collectors.toList());
    }
}
