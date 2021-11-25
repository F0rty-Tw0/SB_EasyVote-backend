package vote.backend.services.PostService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vote.backend.entities.Post.Post;
import vote.backend.repositories.PostRepository;

@Service
public class PostServiceImpl implements PostService {

  @Autowired
  private PostRepository postRepository;

  @Override
  public List<Post> findAllPosts() {
    return postRepository.findAll();
  }

  @Override
  public void addPost(Post post) {
    postRepository.save(post);
  }

  @Override
  public Post findPostByTitle(String title) {
    return postRepository
      .findByTitle(title)
      .orElseThrow(() -> new RuntimeException("Post not found"));
  }

  @Override
  public List<Post> findPostsByAuthorZipCode(String zipCode) {
    return postRepository.findByAuthorZip(zipCode);
  }
}
