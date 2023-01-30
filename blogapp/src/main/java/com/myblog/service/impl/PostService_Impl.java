package com.myblog.service.impl;

import com.myblog.entities.Post;
import com.myblog.exception.ResourceNotFoundException;
import com.myblog.payload.PostDto;
import com.myblog.payload.PostResponse;
import com.myblog.repository.PostRepository;
import com.myblog.service.PostService;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class PostService_Impl implements PostService {

    private PostRepository postRepo;
    private ModelMapper modelMapper;

    public PostService_Impl(PostRepository postRepo,ModelMapper modelMapper) {
        this.postRepo = postRepo;
        this.modelMapper = modelMapper;
    }//this two line of code will perform Autowired(it means that it will perform bean creation...)
    //insert post





    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = mapToEntity(postDto);
        Post postEntity = postRepo.save(post);
        PostDto dto = mapToDto(postEntity);
        return dto;

    }



    //get all post
//    @Override
//    public List<PostDto> getAllPosts(int pageNo,int pageSize) {
//        Pageable pageable = PageRequest.of(pageNo,pageSize);
//        Page<Post> posts = postRepo.findAll(pageable);
//        List<Post> content = posts.getContent();
//        return content.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
//    }          //this is previous one. updated one is below:


    //
    @Override
    public PostResponse getAllPosts(int pageNo,int pageSize,String sortBy,String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                :Sort.by(sortBy).descending();


        Pageable pageable = PageRequest.of(pageNo,pageSize, sort);
        Page<Post> posts = postRepo.findAll(pageable);
        List<Post> content = posts.getContent();

        List<PostDto> contents =  content.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(contents);
        postResponse.setPageNo(posts.getNumber());//it will give me current page no:
        postResponse.setPageSize(posts.getSize());//it will give me page size
        postResponse.setTotalPage(posts.getTotalPages());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setLast(posts.isLast());//it will return boolean value(true, false)

        return postResponse;
    }


    //get post by id
    @Override
    public PostDto getPostById(long id) {
        Post post = postRepo.findById(id).orElseThrow( () -> new ResourceNotFoundException("Post","id",id));
        return mapToDto(post);
//        Optional<Post> byId = postRepo.findById(id);
//        Post post = byId.get();
//        PostDto postDto = mapToDto(post);
//        return postDto;
    }


    //update post
    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        // get post by id from the database
        Post post = postRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", id)
        );

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post updatedPost = postRepo.save(post);
        return mapToDto(updatedPost);

    }


    //delete post by id;
    @Override
    public void deletePostById(long id) {
        Post post = postRepo.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Post","id",id));

        postRepo.deleteById(id);

    }


    public Post mapToEntity(PostDto postDto){
        Post post = modelMapper.map(postDto, Post.class);
//        Post post = new Post();
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());
        return post;

    }
    public PostDto mapToDto(Post post){
        PostDto dto = modelMapper.map(post, PostDto.class);//by using mapper lib.
//        PostDto dto = new PostDto();
//        dto.setId(post.getId());
//        dto.setTitle(post.getTitle());
//        dto.setDescription(post.getDescription());
//        dto.setContent(post.getContent());
        return dto;

    }
}
