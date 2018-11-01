package com.example.springframework.domain;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by User on 10/31/2018.
 */
public class BlogEntryTest {
    @Test
    public void compareTo() throws Exception {
        BlogEntry a = new BlogEntry();
        a.setTimeOfCreation(0);
        a.setVotes(5);

        BlogEntry b = new BlogEntry();
        b.setTimeOfCreation(10+BlogEntry.INTERVAL);
        b.setVotes(5);
        Assert.assertEquals(-1, a.compareTo(b));

        BlogEntry c = new BlogEntry();
        c.setTimeOfCreation(10);
        c.setVotes(3);
        Assert.assertEquals(1, a.compareTo(c));

        BlogEntry d = new BlogEntry();
        d.setTimeOfCreation(BlogEntry.INTERVAL);
        d.setVotes(3);
        Assert.assertEquals(-1, a.compareTo(d));
    }

    @Test
    public void upvote() throws Exception {
        BlogEntry blogEntry = new BlogEntry();
        blogEntry.addUpvoter("a");
        blogEntry.setVotes(1);
        Integer id = blogEntry.getId();


        Assert.assertTrue(blogEntry.upvote("bb"));
        Assert.assertEquals(2, blogEntry.getVotes().intValue());
        Assert.assertEquals("a,bb,", blogEntry.getListOfUpvoters());

        Assert.assertTrue(blogEntry.upvote("b"));
        Assert.assertEquals(3, blogEntry.getVotes().intValue());
        Assert.assertEquals("a,bb,b,", blogEntry.getListOfUpvoters());

        Assert.assertFalse(blogEntry.upvote("b"));
        Assert.assertEquals(3, blogEntry.getVotes().intValue());
        Assert.assertEquals("a,bb,b,", blogEntry.getListOfUpvoters());
    }



    @Test
    public void downvoteBlogEntry() throws Exception {
        BlogEntry blogEntry = new BlogEntry();
        blogEntry.addUpvoter("a");
        blogEntry.setVotes(1);
        Integer id = blogEntry.getId();

        Assert.assertTrue(blogEntry.downvote("bb"));
        Assert.assertEquals(0, blogEntry.getVotes().intValue());
        Assert.assertEquals("a,", blogEntry.getListOfUpvoters());
        Assert.assertEquals("bb,", blogEntry.getListOfDownvoters());

        Assert.assertTrue(blogEntry.downvote( "b"));
        Assert.assertEquals(-1, blogEntry.getVotes().intValue());
        Assert.assertEquals("a,", blogEntry.getListOfUpvoters());
        Assert.assertEquals("bb,b,", blogEntry.getListOfDownvoters());

        Assert.assertFalse(blogEntry.downvote( "b"));
        Assert.assertEquals(-1, blogEntry.getVotes().intValue());
        Assert.assertEquals("a,", blogEntry.getListOfUpvoters());
        Assert.assertEquals("bb,b,", blogEntry.getListOfDownvoters());

    }

}