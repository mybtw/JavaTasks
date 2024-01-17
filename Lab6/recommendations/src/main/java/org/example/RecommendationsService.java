package org.example;


import java.util.List;

public interface RecommendationsService {

    List<User> recommendFriends(Long userId);

}
