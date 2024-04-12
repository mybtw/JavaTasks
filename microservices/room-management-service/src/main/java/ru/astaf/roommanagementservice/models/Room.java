package ru.astaf.roommanagementservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "rooms")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Room {
    @Id
    private String id;
    private String name;
    private String address;
    private int floor;
    private int capacity;
    private boolean hasMonitorOrTV;
    private boolean hasVideoConferencing;
}
