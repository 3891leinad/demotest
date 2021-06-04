package pl.danysoftcompany.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.danysoftcompany.demo.model.Comment;
import pl.danysoftcompany.demo.model.Post;
import pl.danysoftcompany.demo.repository.CommentRepository;
import pl.danysoftcompany.demo.repository.PostRepository;


import javax.transaction.Transactional;
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
    @Cacheable(cacheNames = "SinglePost", key="#id")
    public List<Post> getSinglePost(long id) {
        List<Long> elements = new ArrayList<>();
        elements.add(id);
        return postRepository.findAllById(elements);
    }
    @Cacheable(cacheNames = "PostsWithComments")
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

    public Post addPost(Post post) {
        return postRepository.save(post);
    }

    @Transactional
    @CachePut(cacheNames="SinglePost", key = "#result.id")
    public Post editPost(Post post) {
        Post postEdited = postRepository.findById(post.getId()).orElseThrow();
        postEdited.setTitle(post.getTitle());
        postEdited.setContent(post.getContent());
        return postEdited;
    }
    @CacheEvict(cacheNames="SinglePost")
    public void deletePost(long id) {
        postRepository.deleteById(id);
    }
}
