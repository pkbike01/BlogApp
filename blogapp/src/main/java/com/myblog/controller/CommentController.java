package com.myblog.controller;

import com.myblog.payload.CommentDto;
import com.myblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {

    @Autowired
    private CommentService commentService;


    //create comment by using postId
    @PostMapping("posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable long postId, @RequestBody CommentDto commentDto){
        return new ResponseEntity(commentService.createComment(postId,commentDto), HttpStatus.CREATED);

    }

    //get comments by postId
    //localhost:8080/api/posts/1/comments
    @GetMapping("posts/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getCommentByPostId(@PathVariable long postId){
        return ResponseEntity.ok(commentService.getCommentByPostId(postId));
    }

    //update comment
    //localhost:8080/api/posts/{postId}/comments/{commentId}
    //28/12/22
    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable long postId,
                                                    @PathVariable long commentId,
                                                    @RequestBody CommentDto commentDto){
        CommentDto dto = commentService.updateComment(postId, commentId, commentDto);

        return new ResponseEntity<>(dto, HttpStatus.OK);

    }

    //localhost:8080/api/posts/{postId}/comments/{commentId}
    //delete the record
    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable long postId,
                                                @PathVariable long commentId){
        commentService.deleteComment(postId,commentId);
        return new ResponseEntity<>("Comment has been deleted!!!",HttpStatus.OK);

    }

    //30-01-23
//    Topic --> mapper library








}
