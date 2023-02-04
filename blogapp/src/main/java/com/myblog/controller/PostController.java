package com.myblog.controller;

import com.myblog.payload.PostDto;
import com.myblog.payload.PostResponse;
import com.myblog.service.PostService;

import com.myblog.utils.AppConstant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;
    public PostController(PostService postService)
    {
        this.postService = postService;
    }


    @PreAuthorize("hasRole('ADMIN')")//This is constraint which I'm putting on it . And it will work when role is admin.
    //when we add the annotation (@EnableGlobalMethodSecurity(prePostEnabled = true)) in spring security
    // config class.
    @PostMapping
    public ResponseEntity<Object> createPost(@Valid  @RequestBody PostDto postDto, BindingResult bindingResult){
        //BindingResult acts as a ModelMap But it identifies whether error is there or not.
        if(bindingResult.hasErrors()){
            /* System.out.println(bindingResult.getFieldError()); */
            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);


        }
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);

    //Here, we need spring validation is active. So, we write (@Valid) annotation before save the post.
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
    //localhost:8080/api/posts?pageNo=0&pageSize=10&sortBy=
    //get all posts using rest api
//    localhost:8080/api/posts?pageNo=0&pageSize=5&sortBy=title

    @GetMapping
    public PostResponse getAllPost(@RequestParam(value = "pageNo", defaultValue = AppConstant.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                                   @RequestParam(value = "pageSize", defaultValue = AppConstant.DEFAULT_PAGE_SIZE, required = false) int pageSize,
     /*Here, performing sorting */ @RequestParam(value = "sortBy", defaultValue = AppConstant.DEFAULT_SORT_BY, required = false) String sortBy,
                                   /* http://localhost:8080/api/posts?pageNo=0&pageSize=10&sortBy */
                                   @RequestParam(value = "sortDir", defaultValue = AppConstant.DEFAULT_SORT_DIREC, required = false) String sortDir) {
        return postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
    }


}

