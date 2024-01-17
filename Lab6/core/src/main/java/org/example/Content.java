package org.example;

import java.time.LocalDateTime;

public interface Content {

    Long getId();


    void setId(Long id);


    String getTitle();

    void setTitle(String title);


    String getContent();


    void setContent(String content);


    User getAuthor();


    void setAuthor(User author);


    LocalDateTime getPublishedAt();


    void setPublishedAt(LocalDateTime publishedAt);
}
