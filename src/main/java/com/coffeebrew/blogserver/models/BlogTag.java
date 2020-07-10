package com.coffeebrew.blogserver.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class BlogTag {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    String id;
    @OneToOne(targetEntity = Blog.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "blog_id", referencedColumnName = "id")
    Blog blog;
    @OneToOne(targetEntity = Tag.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "tag_id", referencedColumnName = "id")
    Tag tag;

    public BlogTag() {
    }

    public BlogTag(Blog blog, Tag tag) {
        this.blog = blog;
        this.tag = tag;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
