package com.example.springframework.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Entity
public class BlogEntry implements Comparable<BlogEntry>  {
    public static final long INTERVAL = 60000;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The database generated entry ID")
    private Integer id;
    @ApiModelProperty(notes = "The entry text", required = true)
    private String text;
    @ApiModelProperty(notes = "Number of votes")
    private Integer votes;
    @ApiModelProperty(notes = "List of upvoters", hidden=true)
    private String listOfUpvoters;
    @ApiModelProperty(notes = "List of downvoters", hidden=true)
    private String listOfDownvoters;
    @ApiModelProperty(notes = "Time of blog entry creation", hidden=true)
    private long timeOfCreation;

    public BlogEntry() {
        this.votes =0;
        this.timeOfCreation = (new Date()).getTime();
        listOfUpvoters = "";
        listOfDownvoters="";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    public String getListOfUpvoters() {
        return listOfUpvoters;
    }

    public void setListOfUpvoters(String listOfUpvoters) {
        this.listOfUpvoters = listOfUpvoters;
    }

    public void addUpvoter(String user) {
        listOfUpvoters += user+",";
    }

    public String getListOfDownvoters() {
        return listOfDownvoters;
    }

    public void setListOfDownvoters(String listOfDownvoters) {
        this.listOfDownvoters = listOfDownvoters;
    }

    public void addDownvoter(String user) {
        listOfDownvoters += user+",";
    }

    public long getTimeOfCreation() {
        return timeOfCreation;
    }

    public void setTimeOfCreation(long timeOfCreation) {
        this.timeOfCreation = timeOfCreation;
    }

    public boolean upvote(String user){
        List<String> list = Arrays.asList(getListOfUpvoters().split(","));
        if (!list.contains(user)) {
            setVotes(getVotes() + 1);
            addUpvoter(user);
            return true;
        }
        return false;
    }

    public boolean downvote(String user){
        List<String> list = Arrays.asList(getListOfDownvoters().split(","));
        if (!list.contains(user)) {
            setVotes(getVotes() - 1);
            addDownvoter(user);
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(BlogEntry other) {
        if (other.getTimeOfCreation() -getTimeOfCreation() < INTERVAL) {
            if (getVotes() >= other.getVotes()) {
                return 1;
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }


    @Override
    public String toString() {return "BlogEntry{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", votes=" + votes +
                ", timeOfCreation=" + timeOfCreation +
                '}';
    }
}


