package org.java.training.helpdesk.entity;

import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.Field;
import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Chapter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Field
    private String name;
    private Integer number;

    @Field
    @Type(type="text")
    private String content;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "attachment_id")
    private Attachment attachment;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "chapter_id")
    private Set<Likee> likes;

    @ContainedIn
    @OneToOne
    private Article article;

    public Chapter(){};

    public Chapter(Long id, Integer number, String name, String content, Attachment attachment, Set<Likee> likes) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.content = content;
        this.attachment = attachment;
        this.likes = likes;
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

    public Set<Likee> getLikes() {
        return likes;
    }

    public void setLikes(Set<Likee> likes) {
        this.likes = likes;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chapter chapter = (Chapter) o;
        return Objects.equals(id, chapter.id) &&
                Objects.equals(number, chapter.number) &&
                Objects.equals(name, chapter.name) &&
                Objects.equals(content, chapter.content) &&
                Objects.equals(attachment, chapter.attachment) &&
                Objects.equals(likes, chapter.likes) &&
                Objects.equals(article, chapter.article);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, name, content, attachment, likes);
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", attachment=" + attachment +
                ", likes=" + likes +
                '}';
    }
}