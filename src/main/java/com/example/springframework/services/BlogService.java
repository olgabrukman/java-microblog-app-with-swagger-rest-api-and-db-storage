package com.example.springframework.services;

import com.example.springframework.domain.BlogEntry;

import java.util.List;

public interface BlogService {
    Iterable<BlogEntry> listAllBlogEntries();

    BlogEntry getBlogEntryById(Integer id);

    BlogEntry saveBlogEntry(BlogEntry blogEntry);

    boolean upvoteBlogEntry(Integer id, String user);

    boolean downvoteBlogEntry(Integer id, String user);

    List<BlogEntry> getTopEntries();
}
