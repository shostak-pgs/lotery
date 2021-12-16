package org.java.training.helpdesk.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.java.training.helpdesk.entity.enums.Genre;
import org.java.training.helpdesk.entity.enums.Tag;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ArticleDto {
    private final static String DATE_PATTERN = "dd/MM/yyyy HH:mm";

    private Long id;
    private String name;
    private Genre genre;
    private String description;
    private List<RateDto> rates;

    @JsonFormat(pattern = DATE_PATTERN)
    private Date lastModifyDate;
    private List<Tag> tags;
    private Long userId;

    public ArticleDto() {
    }

    public ArticleDto(Long id, String name, Genre genre, String description, List<Tag> tags,
                      List<RateDto> rates, Date lastModifyDate, Long userId) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.description = description;
        this.tags = tags;
        this.rates = rates;
        this.lastModifyDate = lastModifyDate;
        this.userId = userId;
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

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getLastModifyDate() {
        return lastModifyDate;
    }

    public void setLastModifyDate(Date lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<RateDto> getRates() {
        return rates;
    }

    public void setRates(List<RateDto> rates) {
        this.rates = rates;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleDto that = (ArticleDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                genre == that.genre &&
                Objects.equals(description, that.description) &&
                Objects.equals(rates, that.rates) &&
                Objects.equals(lastModifyDate, that.lastModifyDate) &&
                Objects.equals(tags, that.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, genre, rates, description, lastModifyDate, tags);
    }

    @Override
    public String toString() {
        return "ArticleDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genre=" + genre +
                ", rates=" + rates +
                ", description='" + description + '\'' +
                ", lastModifyDate=" + lastModifyDate +
                ", tags=" + tags +
                '}';
    }
}