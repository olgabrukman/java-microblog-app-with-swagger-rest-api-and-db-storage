package com.example.springframework.topdatastruct;

import com.example.springframework.domain.BlogEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by User on 10/31/2018.
 */
public class Top {
    public static final int CAPACITY = 5;

    private PriorityQueue<BlogEntry> top;

    public Top() {
        this.top = new PriorityQueue<>(CAPACITY);
    }

    public boolean offerToTop(BlogEntry entry) {
        if (top.size() < CAPACITY) {
            return top.offer(entry);
        }
        BlogEntry peek = top.peek();
        if (peek.compareTo(entry) < 0) {
            top.remove(peek);
            return top.offer(entry);
        }
        return false;
    }

    public List<BlogEntry> getTopList() {
        List<BlogEntry> list = new ArrayList<>();
        list.addAll(top);
        return list;
    }
}
