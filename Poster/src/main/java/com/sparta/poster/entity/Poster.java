package com.sparta.poster.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

}
