package org.example;

import java.time.LocalDateTime;

public class MessageImpl implements Message{
    private Long id;
    private Long senderId;

    private Long recipientId;

    private String content;
    private LocalDateTime timestamp;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getSenderId() {
        return senderId;
    }

    @Override
    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    @Override
    public Long getRecipientId() {
        return recipientId;
    }

    @Override
    public void setRecipientId(Long recipientId) {
        this.recipientId = recipientId;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
