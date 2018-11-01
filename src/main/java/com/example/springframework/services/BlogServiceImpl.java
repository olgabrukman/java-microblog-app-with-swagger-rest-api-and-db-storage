package com.example.springframework.services;

import com.example.springframework.domain.BlogEntry;
import com.example.springframework.repositories.BlogRepository;
import com.example.springframework.topdatastruct.Top;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    private BlogRepository blogRepository;
    private Top top;

    public BlogServiceImpl() {
        top = new Top();
    }

    @Autowired
    public void setBlogRepository(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }


    @Override
    public Iterable<BlogEntry> listAllBlogEntries() {
        return blogRepository.findAll();
    }

    @Override
    public BlogEntry getBlogEntryById(Integer id) {
        return blogRepository.findById(id).orElse(null);
    }

    @Override
    public BlogEntry saveBlogEntry(BlogEntry blogEntry) {
        return blogRepository.save(blogEntry);
    }

    @Override
    public boolean upvoteBlogEntry(Integer id, String user) {
        BlogEntry blogEntry = blogRepository.findById(id).orElse(null);
        boolean voteHappened = false;
        if (blogEntry != null){
            voteHappened = blogEntry.upvote(user);
              if (voteHappened) {
                  saveBlogEntry(blogEntry);
                  top.offerToTop(blogEntry);
              }

            }
        return voteHappened;
    }

    @Override
    public boolean downvoteBlogEntry(Integer id, String user) {
        BlogEntry blogEntry = blogRepository.findById(id).orElse(null);
        boolean voteHappened = false;
        if (blogEntry != null){
            voteHappened = blogEntry.downvote(user);
            if (voteHappened) {
                saveBlogEntry(blogEntry);
            }

        }
        return voteHappened;
    }

    @Override
    public List<BlogEntry> getTopEntries() {
        return top.getTopList();
    }
}
