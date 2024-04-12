package ru.astaf.roommanagementservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomSearchCriteria {
    private String name;
    private String address;
    private ZonedDateTime start;
    private ZonedDateTime end;
    private Integer capacity;
    private Integer floor;
    private Boolean hasMonitorOrTV;
    private Boolean hasVideoConferencing;
}
