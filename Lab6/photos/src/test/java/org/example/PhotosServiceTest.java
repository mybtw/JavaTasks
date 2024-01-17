package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class PhotosServiceTest {
    private PhotosService photosService;
    private Photo photo;
    @BeforeEach
    public void setUp() {
        photosService = mock(PhotosService.class);
        photo = new PhotoImpl();
    }

    @Test
    public void givenValidUserIdAndPhoto_whenUploadPhoto_thenPhotoIdReturned() {
        Long userId = 1L;
        when(photosService.uploadPhoto(eq(userId), any(Photo.class))).thenReturn(1L);

        Long photoId = photosService.uploadPhoto(userId, photo);

        assertNotNull(photoId);
        assertEquals(1L, photoId);
        verify(photosService).uploadPhoto(userId, photo);
    }

    @Test
    public void givenValidPhotoId_whenGetPhoto_thenPhotoReturned() {
        Long photoId = 1L;
        when(photosService.getPhoto(photoId)).thenReturn(photo);

        Photo result = photosService.getPhoto(photoId);

        assertNotNull(result);
        verify(photosService).getPhoto(photoId);
    }

    @Test
    public void givenValidUserIdAndAlbumName_whenCreateAlbum_thenAlbumIdReturned() {
        Long userId = 1L;
        String albumName = "My Album";
        when(photosService.createAlbum(userId, albumName)).thenReturn(1L);

        Long albumId = photosService.createAlbum(userId, albumName);

        assertNotNull(albumId);
        assertEquals(1L, albumId);
        verify(photosService).createAlbum(userId, albumName);
    }

    @Test
    public void givenValidAlbumIdAndPhotoId_whenAddPhotoToAlbum_thenTrueReturned() {
        Long albumId = 1L;
        Long photoId = 2L;
        when(photosService.addPhotoToAlbum(albumId, photoId)).thenReturn(true);

        boolean result = photosService.addPhotoToAlbum(albumId, photoId);

        assertTrue(result);
        verify(photosService).addPhotoToAlbum(albumId, photoId);
        verify(photosService, never()).addPhotoToAlbum(80L,90L);
    }
}