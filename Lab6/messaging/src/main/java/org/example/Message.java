package org.example;

import java.time.LocalDateTime;

public interface Message {

    Long getId();


    void setId(Long id);


    Long getSenderId();


    void setSenderId(Long senderId);


    Long getRecipientId();


    void setRecipientId(Long recipientId);


    String getContent();


    void setContent(String content);


    LocalDateTime getTimestamp();


    void setTimestamp(LocalDateTime timestamp);
}
