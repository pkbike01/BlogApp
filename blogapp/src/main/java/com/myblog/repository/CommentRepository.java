package com.myblog.repository;

import com.myblog.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {



    //custom method to find the record based on postId.
    List<Comment> findCommentByPostId(long postId);// I just need to define incomplete method. Then spring boot will do
    //further Things....


}
