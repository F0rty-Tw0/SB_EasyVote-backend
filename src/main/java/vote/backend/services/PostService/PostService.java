package vote.backend.services.PostService;

import java.util.List;
import vote.backend.entities.Post.Comment.Comment;
import vote.backend.entities.Post.Post;

public interface PostService {
  public List<Post> findAllPosts();

  public Post findPostByTitle(String title);

  public List<Post> findPostsByAuthorZipCode(String zipCode);

  public void addCommentToPost(Post post, Comment comment);

  public void addPost(Post post);
}
