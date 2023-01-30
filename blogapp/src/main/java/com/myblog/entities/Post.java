package com.myblog.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="post", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})}
        //to make the column unique
)

public class Post {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(name="title",nullable = false)
    private String title;

    @Column(name="description",nullable = false)
    private String description;

    @Lob//it will give me flexibility to store 1000 of character in content section.
    @Column(name="content", nullable = false)
    private String content;//string can contain only 255 character. but in content 1000 of character consists.
    //so we can use @Lob annotation here..


    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL,orphanRemoval = true)
            /* orphanRemoval is true (it means that automatically it will delete in child table. ): time ->46.39*/
            //caseCade-->it means that Any changes done in parent table will have to  impact on child.
    Set<Comment> comments = new HashSet<>();// I did this because it will not contain duplicate.....
    /**what is biDirectional mapping?
     ans-->OneToMany and ManyToOne mapping it will become bidirectional mapping......*/


}
