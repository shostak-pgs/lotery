package org.java.training.helpdesk.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private byte[] blobBytes;
    private String contentType;
    private String name;

    @OneToOne
    private Chapter chapter;

    public Attachment(){ }

    public Attachment(byte[] blob, String name, String contentType) {
        this.blobBytes = blob;
        this.name = name;
        this.contentType = contentType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getBlobBytes() {
        return blobBytes;
    }

    public void setBlobBytes(byte[] blobBytes) {
        this.blobBytes = blobBytes;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        Attachment that = (Attachment) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(contentType, that.contentType) &&
                Objects.equals(name, that.name) &&
                Objects.equals(chapter, that.chapter);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, contentType, name);
        return result;
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "id=" + id +
                "content length=" + blobBytes.length +
                ", contentType='" + contentType + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}