package com.example.springframework.services;

import com.example.springframework.domain.BlogEntry;
import com.example.springframework.repositories.BlogRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.never;


@RunWith(MockitoJUnitRunner.class)
public class BlogServiceImplSpyTest {
    @Spy
    private BlogServiceImpl blogService;
    @Mock
    private BlogRepository blogRepository;
    @Mock
    private BlogEntry blogEntry;

    @Test(expected=NullPointerException.class)
    public void shouldThrowNullPointerException_whenGetEntryByIdIsCalledWithoutContext() throws Exception {
        BlogEntry retrievedBlogEntry = blogService.getBlogEntryById(5);
        assertThat(retrievedBlogEntry, is(equalTo(blogEntry)));
    }

    public void shouldThrowNullPointerException_whenSaveEntryIsCalledWithoutContext() throws Exception {
        Mockito.doReturn(blogEntry).when(blogRepository).save(blogEntry);
        BlogEntry savedBlogEntry = blogService.saveBlogEntry(blogEntry);
        assertThat(savedBlogEntry, is(equalTo(blogEntry)));
    }

    @Test
    public void shouldVerifyThatGetEntryByIdIsCalled() throws Exception {
        Mockito.doReturn(blogEntry).when(blogService).getBlogEntryById(5);
        BlogEntry retrievedBlogEntry = blogService.getBlogEntryById(5);
        Mockito.verify(blogService).getBlogEntryById(5);
    }

    @Test
    public void shouldVerifyThatSaveEntryIsNotCalled() throws Exception {
        Mockito.doReturn(blogEntry).when(blogService).getBlogEntryById(5);
        BlogEntry retrievedBlogEntry = blogService.getBlogEntryById(5);
        Mockito.verify(blogService,never()).saveBlogEntry(blogEntry);
    }


    @Test
    public void shouldVerifyThatUpvoteHappened() throws Exception {
        Mockito.doReturn(true).when(blogService).upvoteBlogEntry(5, "a");
        boolean result = blogService.upvoteBlogEntry(5, "a");
        Mockito.verify(blogService).upvoteBlogEntry(5, "a");
        Assert.assertTrue(result);
    }

    @Test
    public void shouldVerifyThatUpvoteNotHappened() throws Exception {
        Mockito.doReturn(false).when(blogService).upvoteBlogEntry(5, "a");
        boolean result = blogService.upvoteBlogEntry(5, "a");
        Mockito.verify(blogService).upvoteBlogEntry(5, "a");
        Assert.assertFalse(result);
    }

    @Test
    public void shouldVerifyThatDownvoteHappened() throws Exception {
        Mockito.doReturn(true).when(blogService).downvoteBlogEntry(5, "a");
        boolean result = blogService.downvoteBlogEntry(5, "a");
        Mockito.verify(blogService).downvoteBlogEntry(5, "a");
        Assert.assertTrue(result);
    }

    @Test
    public void shouldVerifyThatDownvoteNotHappened() throws Exception {
        Mockito.doReturn(false).when(blogService).downvoteBlogEntry(5, "a");
        boolean result = blogService.downvoteBlogEntry(5, "a");
        Mockito.verify(blogService).downvoteBlogEntry(5, "a");
        Assert.assertFalse(result);
    }


    @Test
    public void shouldVerifyThatGetTopHappened() throws Exception {
        List<BlogEntry> entries = new ArrayList<>();
        entries.add(blogEntry);
        Mockito.doReturn(entries).when(blogService).getTopEntries();
        List<BlogEntry> topEntries = blogService.getTopEntries();
        Mockito.verify(blogService).getTopEntries();
        Assert.assertEquals(1, topEntries.size());
        Assert.assertEquals(blogEntry, topEntries.get(0));
    }



}