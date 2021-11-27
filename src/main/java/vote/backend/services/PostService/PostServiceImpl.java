package vote.backend.services.PostService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vote.backend.entities.Post.Post;
import vote.backend.errorHandler.ErrorResponseCreator;
import vote.backend.errorHandler.Exceptions.ResourceNotFoundException;
import vote.backend.repositories.PostRepository;

@Service
public class PostServiceImpl implements PostService {

  @Autowired
  private PostRepository postRepository;

  String object = "Post";

  @Override
  public List<Post> findAllPosts() {
    return postRepository.findAll();
  }

  @Override
  public Post findPostByTitle(String title) {
    return postRepository
      .findByTitle(title)
      .orElseThrow(
        () ->
          new ResourceNotFoundException(
            ErrorResponseCreator.notFoundException(object, "title", title)
          )
      );
  }

  @Override
  public List<Post> findPostsByAuthorZipCode(String zipCode) {
    return postRepository.findByAuthorZip(zipCode);
  }

  @Override
  public void addPost(Post post) {
    postRepository.save(post);
  }

  @Override
  public void updatePostById(Post post, Long id) {
    Post postToUpdate = postRepository
      .findById(id)
      .orElseThrow(() -> new RuntimeException("Post not found"));
    postToUpdate.setTitle(post.getTitle());
    postToUpdate.setText(post.getText());
    postRepository.save(postToUpdate);
  }

  @Override
  public void deletePostById(Post post, Long id) {
    Post postToDelete = postRepository
      .findById(id)
      .orElseThrow(() -> new RuntimeException("Post not found"));
    postRepository.delete(postToDelete);
  }
}
