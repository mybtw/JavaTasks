package ru.astaf.roommanagementservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomSearchCriteria {
    private String name;
    private String address;
    private Integer minCapacity;
    private Integer maxCapacity;
    private Boolean hasMonitorOrTV;
    private Boolean hasVideoConferencing;
}
