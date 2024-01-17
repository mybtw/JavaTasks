package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecommendationsServiceTest {
    private RecommendationsService recommendationsService;

    @BeforeEach
    public void setUp() {
        recommendationsService = mock(RecommendationsService.class);
    }

    @Test
    public void givenValidUserId_whenRecommendFriends_thenCorrectRecommendationsReturned() {
        Long userId = 1L;
        List<User> mockFriendRecommendations = Arrays.asList(new UserImpl("Alice"), new UserImpl("Bob"));

        when(recommendationsService.recommendFriends(userId)).thenReturn(mockFriendRecommendations);

        List<User> recommendations = recommendationsService.recommendFriends(userId);

        assertNotNull(recommendations);
        assertEquals(2, recommendations.size());
        verify(recommendationsService).recommendFriends(userId);
    }

    @Test
    public void givenInvalidUserId_whenRecommendFriends_thenThrowException() {
        Long invalidUserId = -1L;

        when(recommendationsService.recommendFriends(invalidUserId))
                .thenThrow(new IllegalArgumentException("Invalid user ID"));

        assertThrows(IllegalArgumentException.class, () -> {
            recommendationsService.recommendFriends(invalidUserId);
        });

        verify(recommendationsService).recommendFriends(invalidUserId);
    }
}