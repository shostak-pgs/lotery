package org.java.training.helpdesk.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Rate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Integer value;

    @OneToOne
    private Article article;

    public Rate() {
    }

    public Rate(Integer value, User user) {
        this.value = value;
        this.user = user;
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

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
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
        Rate rate = (Rate) o;
        return Objects.equals(id, rate.id) &&
                Objects.equals(user, rate.user) &&
                Objects.equals(value, rate.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, value);
    }

    @Override
    public String toString() {
        return "Rate{" +
                "id=" + id +
                ", user=" + user +
                ", value=" + value +
                '}';
    }
}