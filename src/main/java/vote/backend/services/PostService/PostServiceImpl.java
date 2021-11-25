package vote.backend.services.PostService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vote.backend.entities.Post.Comment.Comment;
import vote.backend.entities.Post.Post;
import vote.backend.repositories.PostRepository;
import vote.backend.services.UserService.UserService;

@Service
public class PostServiceImpl implements PostService {

  @Autowired
  private PostRepository postRepository;

  @Autowired
  private UserService userService;

  @Override
  public List<Post> findAllPosts() {
    return postRepository.findAll();
  }

  @Override
  public void addPost(Post post) {
    postRepository.save(post);
    // System.out.println(post.getTitle());
    // userService.addPostToUser(post.getAuthor(), post);
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

  @Override
  public void addCommentToPost(Post post, Comment comment) {
    post.addComment(comment);
    postRepository.save(post);
  }
}
