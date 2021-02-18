package pl.danysoftcompany.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.danysoftcompany.demo.model.Post;
import pl.danysoftcompany.demo.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<Post> getPosts() {
        return postRepository.findAll();
    }
    public List<Post> getSinglePost(long id) {
        List<Long> elements = new ArrayList<>();
        elements.add(id);
        return postRepository.findAllById(elements);
    }
}
