package pl.danysoftcompany.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.danysoftcompany.demo.model.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPostIdIn(List<Long> ids);
}
