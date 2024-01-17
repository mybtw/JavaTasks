package org.example;

import java.util.List;

public interface MessagingService {

    boolean sendMessage(Long senderId, Long recipientId, String message);


    List<Message> getConversation(Long userId1, Long userId2);

    Long createChat(List<Long> participants);

    boolean addParticipantToChat(Long chatId, Long userId);
}
