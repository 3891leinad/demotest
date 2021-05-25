package pl.danysoftcompany.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.danysoftcompany.demo.model.Post;
import pl.danysoftcompany.demo.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private static final int PAGE_SIZE = 20;
    private final PostRepository postRepository;

    public List<Post> getPosts(int page) {
        return postRepository.findAllPosts(PageRequest.of(page,PAGE_SIZE));
    }
    public List<Post> getSinglePost(long id) {
        List<Long> elements = new ArrayList<>();
        elements.add(id);
        return postRepository.findAllById(elements);
    }
}
