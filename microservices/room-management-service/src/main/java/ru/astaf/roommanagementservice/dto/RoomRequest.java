package ru.astaf.roommanagementservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomRequest {
    private String name;
    private String address;
    private int capacity;
    private int floor;
    private boolean hasMonitorOrTV;
    private boolean hasVideoConferencing;
}
