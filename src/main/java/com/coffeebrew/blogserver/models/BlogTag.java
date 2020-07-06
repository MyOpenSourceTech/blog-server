package com.coffeebrew.blogserver.models;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Reference;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BlogTag {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    String id;
    @Reference(Blog.class)
    String blogId;
    @Reference(Tag.class)
    String tagId;

    public BlogTag() {
    }

    public BlogTag(String blogId, String tagId) {
        this.blogId = blogId;
        this.tagId = tagId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBlogId() {
        return blogId;
    }

    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }
}
