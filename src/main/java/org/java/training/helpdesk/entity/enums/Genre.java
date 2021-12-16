package org.java.training.helpdesk.entity.enums;

import java.util.Optional;

public enum Genre {
    EMPTY(""),
    FANTASY("FANTASY"),
    ADVENTURE("ADVENTURE"),
    HORROR("HORROR"),
    MYSTERY("MYSTERY");

    private String stringValue;

    Genre(String stringValue) {
        this.stringValue = stringValue;
    }

    public static Genre fromString(String stringValue) {
        Genre newGenre = EMPTY;
        for (Genre genre : values()) {
            if (genre.getStringValue().equalsIgnoreCase(stringValue)) {
                newGenre = genre;
            }
        }
        return newGenre;
    }

    public String getStringValue() {
        return stringValue;
    }
}