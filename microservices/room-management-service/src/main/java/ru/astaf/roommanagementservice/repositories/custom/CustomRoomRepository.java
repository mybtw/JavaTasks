package ru.astaf.roommanagementservice.repositories.custom;

import org.springframework.data.mongodb.core.query.Query;
import ru.astaf.roommanagementservice.models.Room;

import java.util.List;

public interface CustomRoomRepository {
    List<Room> getRoomsBySearchCriteria(Query query);
}
