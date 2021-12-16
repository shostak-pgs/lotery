package org.java.training.helpdesk.dto;

import org.java.training.helpdesk.entity.Attachment;
import java.util.Objects;

public class ChapterDto {
    private Long id;
    private Integer number;
    private String name;
    private String content;
    private Attachment attachment;
    private Integer likes;

    public ChapterDto(Long id, Integer number, String name, String content, Integer likes, Attachment attachment) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.content = content;
        this.attachment = attachment;
        this.likes = likes;
    }

    public ChapterDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Attachment getAttachment() {
        return attachment;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChapterDto that = (ChapterDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(number, that.number) &&
                Objects.equals(name, that.name) &&
                Objects.equals(content, that.content) &&
                Objects.equals(attachment, that.attachment) &&
                Objects.equals(likes, that.likes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, name, content, attachment, likes);
    }

    @Override
    public String toString() {
        return "ChapterDto{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", attachment=" + attachment +
                ", likes=" + likes +
                '}';
    }
}