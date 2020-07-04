package com.coffeebrew.blogserver.builders;

import com.coffeebrew.blogserver.models.Blog;

public class BlogBuilder {
    private Blog blog;

    public BlogBuilder withDefault() {
        blog = new Blog();
        return this;
    }

    public BlogBuilder withTitle(String title) {
        blog.setTitle(title);
        return this;
    }

    public BlogBuilder withBody(String body) {
        blog.setBody(body);
        return this;
    }

    public BlogBuilder withCreatedBy(String createdBy) {
        blog.setCreatedBy(createdBy);
        return this;
    }

    public Blog build() {
        return blog;
    }
}
