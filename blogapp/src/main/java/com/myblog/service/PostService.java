package com.myblog.service;

import com.myblog.payload.PostDto;
import com.myblog.payload.PostResponse;



public interface PostService {
    //Create Post
    PostDto createPost(PostDto postDto);

    //Get All Posts
    PostResponse getAllPosts(int pageNO, int pageSize, String sortBy,String sortDir);

    //Get post by id;
    PostDto getPostById(long id);

    //update post
    PostDto updatePost(PostDto postDto,long id);

    //delete post
    void deletePostById(long id);
}
