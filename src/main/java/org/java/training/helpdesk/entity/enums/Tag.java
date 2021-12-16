package org.java.training.helpdesk.entity.enums;

public enum Tag {
    EMPTY(""),
    MIDDLE_EARTH("Middle-Earth"),
    MORDOR("Mordor"),
    SAURON("Sauron");

    private String stringValue;

    Tag(String stringValue) {
        this.stringValue = stringValue;
    }

    public static Tag fromString(String stringValue) {
        Tag newTag = EMPTY;
        for (Tag tag : values()) {
            if (tag.getStringValue().equalsIgnoreCase(stringValue)) {
                newTag = tag;
            }
        }
        return newTag;
    }

    public String getStringValue() {
        return stringValue;
    }
}