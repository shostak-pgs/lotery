package org.java.training.helpdesk.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.Field;
import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Comment {
    private final static String DATE_PATTERN = "dd/MM/yyyy HH:mm";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Field
    private String text;

    @JsonFormat(pattern = "dd/MM/yyyy" )
    private Date createdDate;

    @ContainedIn
    @OneToOne
    private Article article;

    public Comment() {}

    public Comment(User user, String text, Date createdDate, Article article) {
        this.user = user;
        this.text = text;
        this.createdDate = createdDate;
        this.article = article;
    }

    public static String getDatePattern() {
        return DATE_PATTERN;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id) &&
                Objects.equals(user, comment.user) &&
                Objects.equals(text, comment.text) &&
                Objects.equals(createdDate, comment.createdDate) &&
                Objects.equals(article, comment.article);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, text, createdDate, article);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", user=" + user +
                ", text='" + text + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}