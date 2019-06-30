Specifications
======
Micro blog will have simple text as news posts.

We want to create a RESTful JSON API to handle the post resource (create, update, read, etc). The posts and votes should be saved in a storage engine of your choice. The service accepts json requests and responds with a json response. This system will also support upvoting and downvoting a post.

Web service (RESTFul API)
This service will allow the following actions:
1. A user can create a new post providing its text.
2. A user can update an existing post’s text.
3. A user can upvote or downvote a post, but only once.
4. A user can request for a list of “Top Posts”. 
  * Top posts should be determined by a combination of upvotes and creation time of a post.
  * “Top Posts” list is being asked thousands of time per second and the number of posts in the database may be very high.

Usage
=====
* To run the application: run SpringBootWebApplication
* Storage: in-memory h2 database
* See REST UI by accessing: http://localhost:8080/swagger-ui.html#/blog-controller
* Top  contains 5 entries, at the beginning the top is empty. Once entries are upvoted top is filled.
* Top relevance time is 1 minute (i.e., after 1 minute a post will be removed from top if there is a newer entry with at least 1 vote).
