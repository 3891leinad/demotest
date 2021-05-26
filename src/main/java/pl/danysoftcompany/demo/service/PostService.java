package pl.danysoftcompany.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.danysoftcompany.demo.model.Comment;
import pl.danysoftcompany.demo.model.Post;
import pl.danysoftcompany.demo.repository.CommentRepository;
import pl.danysoftcompany.demo.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private static final int PAGE_SIZE = 20;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public List<Post> getPosts(int page, Sort.Direction sort) {
        return postRepository.findAllPosts(PageRequest.of(page,PAGE_SIZE, Sort.by(sort, "id")));
    }
    public List<Post> getSinglePost(long id) {
        List<Long> elements = new ArrayList<>();
        elements.add(id);
        return postRepository.findAllById(elements);
    }

    public List<Post> getPostsWithComments(int page, Sort.Direction sort) {
        List<Post> allPost = postRepository.findAllPosts(PageRequest.of(page,PAGE_SIZE, Sort.by(sort, "id")));
        List<Long> ids = allPost.stream()
                .map(Post::getId)
                .collect(Collectors.toList());
        List<Comment> comments = commentRepository.findAllByPostIdIn(ids);
        allPost.forEach(post -> post.setComment(exctractComments(comments,post.getId())));
        return allPost;
    }

    private List<Comment> exctractComments(List<Comment> comments, long id) {
        return comments.stream()
                .filter(comment -> comment.getPostId() == id)
                .collect(Collectors.toList());
    }
}
