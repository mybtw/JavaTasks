package org.example;

import java.time.LocalDateTime;

public interface Photo {

    Long getId();

    void setId(Long id);


    String getTitle();


    void setTitle(String title);


    String getDescription();


    void setDescription(String description);


    User getAuthor();


    void setAuthor(User author);

    /**
     * Получает дату и время загрузки фотографии.
     *
     * @return Дата и время загрузки фотографии.
     */
    LocalDateTime getUploadTime();

    /**
     * Устанавливает дату и время загрузки фотографии.
     *
     * @param uploadTime Дата и время загрузки фотографии.
     */
    void setUploadTime(LocalDateTime uploadTime);
}
