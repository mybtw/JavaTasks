package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FriendsServiceTest {
    private FriendsService friendsService;

    @BeforeEach
    public void setUp() {
        friendsService = mock(FriendsService.class);
    }

    @Test
    public void givenValidUserIdAndFriendId_whenAddFriend_thenTrueReturned() {
        Long userId = 1L;
        Long friendId = 2L;
        when(friendsService.addFriend(userId, friendId)).thenReturn(true);

        boolean result = friendsService.addFriend(userId, friendId);

        assertTrue(result);
        verify(friendsService).addFriend(userId, friendId);
    }

    @Test
    public void givenValidUserIdAndFriendId_whenRemoveFriend_thenTrueReturned() {
        Long userId = 1L;
        Long friendId = 2L;
        when(friendsService.removeFriend(userId, friendId)).thenReturn(true);

        boolean result = friendsService.removeFriend(userId, friendId);

        assertTrue(result);
        verify(friendsService).removeFriend(userId, friendId);
    }

    @Test
    public void givenValidUserId_whenGetFriends_thenListOfFriendsReturned() {
        Long userId = 1L;
        List<User> mockFriendsList = Arrays.asList(new UserImpl("Charlie"), new UserImpl("Bob"));
        when(friendsService.getFriends(userId)).thenReturn(mockFriendsList);

        List<User> friends = friendsService.getFriends(userId);

        assertNotNull(friends);
        assertEquals(2, friends.size());
        verify(friendsService).getFriends(userId);
    }

    @Test
    public void givenValidSearchTerm_whenSearchUsers_thenListOfUsersReturned() {
        String searchTerm = "John";
        List<User> mockUsersList = Arrays.asList(new UserImpl("John"), new UserImpl("John"));
        when(friendsService.searchUsers(searchTerm)).thenReturn(mockUsersList);

        List<User> users = friendsService.searchUsers(searchTerm);

        assertNotNull(users);
        assertEquals(2, users.size());
        verify(friendsService).searchUsers(searchTerm);
    }
}