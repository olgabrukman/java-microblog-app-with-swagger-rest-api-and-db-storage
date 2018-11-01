package com.example.springframework.topdatastruct;

import com.example.springframework.domain.BlogEntry;
import org.junit.Assert;
import org.junit.Test;

import static com.example.springframework.domain.BlogEntry.INTERVAL;

/**
 * Created by User on 10/31/2018.
 */
public class TopTest {
    @Test
    public void offerToTop() throws Exception {
        Top top = new Top();
        for (int i = 0; i < Top.CAPACITY; i++) {
            BlogEntry entry = new BlogEntry();
            entry.setText("" + i);
            entry.setId(i);
            entry.setVotes(i);
            Assert.assertTrue(top.offerToTop(entry));
        }
        Assert.assertEquals(Top.CAPACITY, top.getTopList().size());


        BlogEntry entry = new BlogEntry();
        entry.setText("new1");
        entry.setId(Top.CAPACITY + 1);
        Assert.assertFalse(top.offerToTop(entry));
        Assert.assertEquals(Top.CAPACITY, top.getTopList().size());

        entry = new BlogEntry();
        entry.setText("new2");
        entry.setId(Top.CAPACITY + 2);
        entry.setVotes(Top.CAPACITY);
        long timeOfCreation = entry.getTimeOfCreation();
        Assert.assertTrue(top.offerToTop(entry));
        Assert.assertEquals(Top.CAPACITY, top.getTopList().size());


        entry = new BlogEntry();
        entry.setText("new3");
        entry.setId(Top.CAPACITY + 3);
        entry.setTimeOfCreation(timeOfCreation + INTERVAL);
        Assert.assertTrue(top.offerToTop(entry));
        Assert.assertEquals(Top.CAPACITY, top.getTopList().size());
    }

}