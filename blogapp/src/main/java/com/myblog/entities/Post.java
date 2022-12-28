package com.myblog.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(
        name="post"
        //uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})}  //to make the column unique

        )
public class Post {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(name="title",nullable = false,unique = true)
    private String title;

    @Column(name="description",nullable = false)
    private String description;

    @Lob//it will give me flexibility to store 1000 of character write in content section.
    @Column(name="content", nullable = false)
    private String content;//string can contain only 255 character. but in content 1000 of character consists.
    //so we can use @Lob annotation here..

}
