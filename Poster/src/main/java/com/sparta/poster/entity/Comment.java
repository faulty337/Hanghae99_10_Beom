package com.sparta.poster.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
public class Comment extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "poster_id")
    private Poster poster;

    @ManyToOne
    private User user;

    private String content;

//    public Comment(User user) {
//        this.user = user;
//    }

    public void update(String content){
        this.content = content;
    }
}
