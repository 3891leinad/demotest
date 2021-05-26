package pl.danysoftcompany.demo.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
@Getter
@Setter
public class Post {
    @Id
    private long id;
    private String title;
    private String content;
    private LocalDateTime created;

    @OneToMany
    @JoinColumn(name="postId")
    private List<Comment> comment;
}
