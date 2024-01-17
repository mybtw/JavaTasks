package org.example;

import java.util.List;

public interface Group {

    Long getId();


    void setId(Long id);


    String getName();


    void setName(String name);


    String getDescription();


    void setDescription(String description);


    List<User> getMembers();


    boolean addMember(User user);


    boolean removeMember(User user);
}
