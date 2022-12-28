package com.myblog.controller;

import com.myblog.payload.PostDto;
import com.myblog.service.PostService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {



    private PostService postService;

    public PostController(PostService postService)
    {
        this.postService = postService;
    }


    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);


    }
//    //get all posts using rest api
//    @GetMapping
//    public List<PostDto> getAllPost(){
//        return postService.getAllPosts();
//    }
    //get post by id;
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }


    //update post by id;
    @PutMapping("/{id}")//here we pass some primary key value.
    public ResponseEntity<PostDto> updatePostById(@RequestBody PostDto postDto,@PathVariable("id") long id){
        PostDto postResponse = postService.updatePost(postDto, id);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }


    //delete post by id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<PostDto> deletePostById(@PathVariable("id") long id){
         postService.deletePostById(id);
        ResponseEntity tResponseEntity = new ResponseEntity<>("post entity deleted successfully!", HttpStatus.OK);
        return tResponseEntity;
    }

    //pagination and sorting (it provides by jpa repository....)
    //url like this
    //localhost:8080/api/posts?pageNo=0&pageSize=10
    //get all posts using rest api
    @GetMapping
    public List<PostDto> getAllPost(@RequestParam(value = "pageNo",defaultValue = "0",required = false) int pageNo,
                                    @RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize){
        return postService.getAllPosts(pageNo,pageSize
        );
    }







}
