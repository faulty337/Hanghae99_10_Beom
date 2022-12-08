package com.sparta.poster.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Poster extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String username;

    private String content;

    @OneToMany(mappedBy = "poster")
    private List<Comment> commentList = new ArrayList<>();
    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

    public void addComment(Comment comment){
        this.commentList.add(comment);
        System.out.println("size : " + commentList.size());
    }
}
