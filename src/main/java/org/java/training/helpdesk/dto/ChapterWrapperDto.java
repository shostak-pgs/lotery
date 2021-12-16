package org.java.training.helpdesk.dto;

import org.springframework.web.multipart.MultipartFile;
import java.util.Objects;

public class ChapterWrapperDto {
    private Long id;
    private String name;
    private Integer number;
    private String content;
    private MultipartFile file;
    private Long articleId;

    public ChapterWrapperDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
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
        ChapterWrapperDto that = (ChapterWrapperDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(number, that.number) &&
                Objects.equals(content, that.content) &&
                Objects.equals(file, that.file) &&
                Objects.equals(articleId, that.articleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, number, content, file, articleId);
    }

    @Override
    public String toString() {
        return "ChapterWrapperDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", content='" + content + '\'' +
                ", file=" + file +
                ", articleId=" + articleId +
                '}';
    }
}
