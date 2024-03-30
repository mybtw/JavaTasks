package ru.astaf.roommanagementservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.astaf.roommanagementservice.models.Room;
import ru.astaf.roommanagementservice.repositories.custom.CustomRoomRepository;

public interface RoomRepository extends MongoRepository<Room, String>, CustomRoomRepository {
}
