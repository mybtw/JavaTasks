package org.example;



import java.util.List;

public interface FriendsService {

    boolean addFriend(Long userId, Long friendId);

    boolean removeFriend(Long userId, Long friendId);


    List<User> getFriends(Long userId);


    List<User> searchUsers(String searchTerm);
}
