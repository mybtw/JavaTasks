package org.example;

import java.time.LocalDateTime;

public interface Event {

    Long getId();


    void setId(Long id);


    String getTitle();


    void setTitle(String title);


    String getDescription();


    void setDescription(String description);


    LocalDateTime getStartTime();


    void setStartTime(LocalDateTime startTime);


    LocalDateTime getEndTime();


    void setEndTime(LocalDateTime endTime);


    String getLocation();


    void setLocation(String location);
}
