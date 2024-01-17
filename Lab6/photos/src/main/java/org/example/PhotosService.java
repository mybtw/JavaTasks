package org.example;

public interface PhotosService {

    Long uploadPhoto(Long userId, Photo photo);

    boolean deletePhoto(Long userId, Long photoId);


    Photo getPhoto(Long photoId);


    Long createAlbum(Long userId, String album);


    boolean addPhotoToAlbum(Long albumId, Long photoId);
}
