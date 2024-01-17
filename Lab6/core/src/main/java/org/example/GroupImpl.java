package org.example;

import java.util.List;

public class GroupImpl implements Group{

    private Long id;
    private String name;
    private String description;
    private List<User> members;
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public List<User> getMembers() {
        return members;
    }

    @Override
    public boolean addMember(User user) {
        return this.members.add(user);
    }

    @Override
    public boolean removeMember(User user) {
        return this.members.remove(user);
    }
}
