package org.java.training.helpdesk.dto;

import java.util.Objects;

public class RateDto {
    private Long id;
    private UserDto userDto;
    private Integer value;
    private ArticleDto articleDto;

    public RateDto(Long id, UserDto userDto, Integer value) {
        this.id = id;
        this.userDto = userDto;
        this.value = value;
    }

    public RateDto() {
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

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public ArticleDto getArticleDto() {
        return articleDto;
    }

    public void setArticleDto(ArticleDto articleDto) {
        this.articleDto = articleDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RateDto rateDto = (RateDto) o;
        return Objects.equals(id, rateDto.id) &&
                Objects.equals(userDto, rateDto.userDto) &&
                Objects.equals(value, rateDto.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userDto, value);
    }

    @Override
    public String toString() {
        return "RateDto{" +
                "id =" + id +
                ", userDto =" + userDto +
                ", value =" + value +
                '}';
    }
}