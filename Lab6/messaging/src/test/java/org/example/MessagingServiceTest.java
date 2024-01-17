package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MessagingServiceTest {
    private MessagingService messagingService;

    @BeforeEach
    public void setUp() {
        messagingService = mock(MessagingService.class);
    }

    @Test
    public void givenValidSenderAndRecipient_whenSendMessage_thenTrueReturned() {
        Long senderId = 1L;
        Long recipientId = 2L;
        String message = "Hello";
        when(messagingService.sendMessage(senderId, recipientId, message)).thenReturn(true);

        boolean result = messagingService.sendMessage(senderId, recipientId, message);

        assertTrue(result);
        verify(messagingService).sendMessage(senderId, recipientId, message);
    }

    @Test
    public void givenTwoUserIds_whenGetConversation_thenListOfMessagesReturned() {
        Long userId1 = 1L;
        Long userId2 = 2L;
        List<Message> mockMessages = Arrays.asList(mock(Message.class), mock(Message.class));
        when(messagingService.getConversation(userId1, userId2)).thenReturn(mockMessages);

        List<Message> messages = messagingService.getConversation(userId1, userId2);

        assertNotNull(messages);
        assertEquals(2, messages.size());
        verify(messagingService).getConversation(userId1, userId2);
    }

    @Test
    public void givenListOfParticipants_whenCreateChat_thenChatIdReturned() {
        List<Long> participants = Arrays.asList(1L, 2L);
        when(messagingService.createChat(participants)).thenReturn(1L);

        Long chatId = messagingService.createChat(participants);

        assertNotNull(chatId);
        assertEquals(1L, chatId);
        verify(messagingService).createChat(participants);
    }

    @Test
    public void givenChatIdAndUserId_whenAddParticipantToChat_thenTrueReturned() {
        Long chatId = 1L;
        Long userId = 2L;
        when(messagingService.addParticipantToChat(chatId, userId)).thenReturn(true);

        boolean result = messagingService.addParticipantToChat(chatId, userId);

        assertTrue(result);
        verify(messagingService).addParticipantToChat(chatId, userId);
    }

    @Test
    public void givenInvalidParameters_whenSendMessage_thenExceptionThrown() {
        Long senderId = null;
        Long recipientId = 2L;
        String message = "Hello";

        doThrow(new IllegalArgumentException("Sender ID is null"))
                .when(messagingService).sendMessage(senderId, recipientId, message);

        assertThrows(IllegalArgumentException.class, () -> {
            messagingService.sendMessage(senderId, recipientId, message);
        });

        verify(messagingService).sendMessage(senderId, recipientId, message);
    }
}