package pl.danysoftcompany.demo.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String content;
    private LocalDateTime created;
    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name="postId", updatable = false, insertable = false)
    private List<Comment> comment;
}
