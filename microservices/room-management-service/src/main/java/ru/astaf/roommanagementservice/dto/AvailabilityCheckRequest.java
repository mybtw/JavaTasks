package ru.astaf.roommanagementservice.dto;

import java.time.ZonedDateTime;
import java.util.List;

public class AvailabilityCheckRequest {
    private List<String> roomIds;
    private ZonedDateTime start;
    private ZonedDateTime end;
}
