package org.java.training.helpdesk.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Likee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    private Chapter chapter;

    public Likee(){}

    public Likee(User user, Chapter chapter) {
        this.user = user;
        this.chapter = chapter;
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

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Likee like = (Likee) o;
        return Objects.equals(id, like.id) &&
                Objects.equals(chapter, like.chapter) &&
                Objects.equals(user, like.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user);
    }

    @Override
    public String toString() {
        return "Likee{" +
                "id=" + id +
                ", user=" + user +
                '}';
    }
}