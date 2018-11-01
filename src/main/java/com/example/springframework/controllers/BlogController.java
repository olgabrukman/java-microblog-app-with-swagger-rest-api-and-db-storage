package com.example.springframework.controllers;

import com.example.springframework.domain.BlogEntry;
import com.example.springframework.services.BlogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blog")
@Api(value="blog", description="Microblog interface")
public class BlogController {

    private BlogService blogService;

    @Autowired
    public void setBlogService(BlogService blogService) {
        this.blogService = blogService;
    }

    @ApiOperation(value = "View a list of blog entries",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 405, message = "You are not allowed to update this resource")
    }
    )

    @RequestMapping(value = "/list", method= RequestMethod.GET, produces = "application/json")
    public Iterable<BlogEntry> list(Model model){
        return blogService.listAllBlogEntries();
    }

    @ApiOperation(value = "Search a product with an ID",response = BlogEntry.class)
    @RequestMapping(value = "/show/{id}", method= RequestMethod.GET, produces = "application/json")
    public BlogEntry showBlogEntry(@PathVariable Integer id, Model model){
        return blogService.getBlogEntryById(id);
    }

    @ApiOperation(value = "Add a blog entry")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity saveProduct(@RequestBody String text){
        BlogEntry blogEntry = new BlogEntry();
        blogEntry.setText(text);
        blogService.saveBlogEntry(blogEntry);
        return new ResponseEntity("BlogEntry saved successfully, id: "+blogEntry.getId(), HttpStatus.OK);
    }

    @ApiOperation(value = "Update a blog entry")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity updateBlogEntryt(@PathVariable Integer id, @RequestBody String text){
        BlogEntry storedBlogEntry = blogService.getBlogEntryById(id);
        storedBlogEntry.setText(text);
        blogService.saveBlogEntry(storedBlogEntry);
        return new ResponseEntity("BlogEntry updated successfully, id: "+storedBlogEntry.getId() + storedBlogEntry.getId(), HttpStatus.OK);
    }


    @ApiOperation(value = "Upvote a blog entry")
    @RequestMapping(value = "/upvote/{id}/{user}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity upvoteBlogEntry(@PathVariable Integer id, @PathVariable String user){
        BlogEntry storedBlogEntry = blogService.getBlogEntryById(id);
        boolean result = blogService.upvoteBlogEntry(id, user);
        blogService.saveBlogEntry(storedBlogEntry);
        if (result) {
            return new ResponseEntity("BlogEntry updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity("You have already upvoted this entry", HttpStatus.FORBIDDEN);
    }

    @ApiOperation(value = "Downvote a blog entry")
    @RequestMapping(value = "/downvote/{id}/{user}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity downvoteBlogEntry(@PathVariable Integer id, @PathVariable String user){
        BlogEntry storedBlogEntry = blogService.getBlogEntryById(id);
        boolean result = blogService.downvoteBlogEntry(id, user);
        blogService.saveBlogEntry(storedBlogEntry);
        if (result) {
            return new ResponseEntity("BlogEntry updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity("You have already downvoted this entry", HttpStatus.FORBIDDEN);
    }

    @RequestMapping(value = "/top", method= RequestMethod.GET, produces = "application/json")
    public Iterable<BlogEntry> top(Model model)
    {
       return blogService.getTopEntries();
    }


}
