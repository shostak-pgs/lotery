package org.java.training.helpdesk.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.java.training.helpdesk.entity.User;
import java.util.Date;
import java.util.Objects;

public class CommentDto {
    private final static String DATE_PATTERN = "dd/MM/yyyy HH:mm";

    private Long id;
    private UserDto userDto;
    private String text;

    @JsonFormat(pattern = DATE_PATTERN)
    private Date createdDate;
    private Long articleId;

    public CommentDto(Long id, UserDto userDto, String text, Date createdDate, Long articleId) {
        this.id = id;
        this.userDto = userDto;
        this.text = text;
        this.createdDate = createdDate;
        this.articleId = articleId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDto getUser() {
        return userDto;
    }

    public void setUser(UserDto userDto) {
        this.userDto = userDto;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentDto that = (CommentDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(userDto, that.userDto) &&
                Objects.equals(text, that.text) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(articleId, that.articleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userDto, text, createdDate, articleId);
    }

    @Override
    public String toString() {
        return "CommentDto{" +
                "id=" + id +
                ", userDto=" + userDto +
                ", text='" + text + '\'' +
                ", createdDate=" + createdDate +
                ", chapterId=" + articleId +
                '}';
    }
}