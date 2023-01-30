package com.myblog.payload;


import lombok.Data;

import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
public class PostDto {
//spring validation has been done here.
    private long id;
    @NotNull     //validation is a extension of hibernate.
    @Size(min=2,message = "Post title should have at least 2 character!")

    private String title;

    @NotNull
    @Size(min = 10,message = "Post Description should have at least 10 characters or more than 10 character! ")
    private String description;


    @NotNull
    @NotBlank
    private String content;

}
