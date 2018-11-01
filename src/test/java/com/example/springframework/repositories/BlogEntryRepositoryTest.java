package com.example.springframework.repositories;

import com.example.springframework.configuration.RepositoryConfiguration;
import com.example.springframework.domain.BlogEntry;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RepositoryConfiguration.class})
public class BlogEntryRepositoryTest {
    private BlogRepository blogRepository;
    @Autowired
    public void setBlogRepository(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }
    @Test
    public void testSaveProduct(){
        //setup blogEntry
        BlogEntry blogEntry = new BlogEntry();
        blogEntry.setText("apple");
        //save blogEntry, verify has ID value after save
        assertNull(blogEntry.getId());
        blogRepository.save(blogEntry);
        assertNotNull(blogEntry.getId());

        //fetch from DB
        BlogEntry fetchedBlogEntry = blogRepository.findById(blogEntry.getId()).orElse(null);
        assertNotNull(fetchedBlogEntry);
        assertEquals(blogEntry.getId(), fetchedBlogEntry.getId());
        assertEquals(blogEntry.getText(), fetchedBlogEntry.getText());

        //update and save
        fetchedBlogEntry.setText("orange");
        blogRepository.save(fetchedBlogEntry);
        BlogEntry fetchedUpdatedBlogEntry = blogRepository.findById(fetchedBlogEntry.getId()).orElse(null);
        assertEquals(fetchedBlogEntry.getText(), fetchedUpdatedBlogEntry.getText());

        //verify count of entries in DB
        long productCount = blogRepository.count();
        assertEquals(productCount, 1);
        //get all entries, list should only have one
        Iterable<BlogEntry> entries = blogRepository.findAll();
        int count = 0;
        for(BlogEntry p : entries){
            count++;
        }
        assertEquals(count, 1);


    }
}
