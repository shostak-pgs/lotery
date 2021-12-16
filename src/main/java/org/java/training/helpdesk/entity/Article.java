package org.java.training.helpdesk.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.search.annotations.*;
import org.java.training.helpdesk.entity.enums.Genre;
import org.java.training.helpdesk.entity.enums.Tag;
import javax.persistence.*;
import java.util.*;

@Indexed
@Entity
public class Article {
    private final static String DATE_PATTERN = "dd/MM/yyyy HH:mm";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Field
    private String name;

    @Field
    private  String description;

    @JsonFormat(pattern = DATE_PATTERN)
    private Date lastModifiedDate;

    @IndexedEmbedded(depth=1)
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private User user;

    @Field
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @IndexedEmbedded
    @Field
    @ElementCollection(targetClass = Tag.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable
    private List<Tag> tags;

    @IndexedEmbedded(depth=1)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "article_id")
    private Set<Chapter> chapters;

    @IndexedEmbedded(depth=1)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "article_id")
    private List<Comment> comments;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "article_id")
    private List<Rate> rates;

    public Article(){}

    public Article(Long id, String name, String description, User user, Genre genre, List<Tag> tags, Date lastModifiedDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.user = user;
        this.genre = genre;
        this.tags = tags;
        this.rates = new ArrayList<>();
        this.chapters = new HashSet<>();
        this.lastModifiedDate = lastModifiedDate;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Set<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(Set<Chapter> chapters) {
        this.chapters = chapters;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public List<Rate> getRates() {
        return rates;
    }

    public void setRates(List<Rate> rates) {
        this.rates = rates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(id, article.id) &&
                Objects.equals(name, article.name) &&
                Objects.equals(description, article.description) &&
                genre == article.genre &&
                Objects.equals(tags, article.tags) &&
                Objects.equals(chapters, article.chapters) &&
                Objects.equals(rates, article.rates) &&
                Objects.equals(lastModifiedDate, article.lastModifiedDate) &&
                Objects.equals(comments, article.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, genre, tags, chapters, rates, comments, lastModifiedDate);
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", genre=" + genre +
                ", tags=" + tags +
                ", chapters=" + chapters +
                ", rates=" + rates +
                ", lastModifiedDate=" + lastModifiedDate +
                ", comments=" + comments +
                '}';
    }
}