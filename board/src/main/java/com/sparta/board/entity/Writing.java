package com.sparta.board.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Writing extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;


    private void setTitle(String title){

    }
    public void update(String title, String contents){
        this.title = title;
        this.content = contents;
    }



    public Writing(String username, String password, String title, String content) {
        this.username = username;
        this.password = password;
        this.title = title;
        this.content = content;
    }
}
