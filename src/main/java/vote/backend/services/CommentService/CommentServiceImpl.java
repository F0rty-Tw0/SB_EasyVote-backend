package vote.backend.services.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vote.backend.entities.Post.Comment.Comment;
import vote.backend.repositories.CommentRepository;
import vote.backend.services.PostService.PostService;
import vote.backend.services.UserService.UserService;

@Service
public class CommentServiceImpl implements CommentService {

  @Autowired
  private CommentRepository commentRepository;

  @Autowired
  private PostService postService;

  @Autowired
  private UserService userService;

  @Override
  public void addComment(Comment comment) {
    commentRepository.save(comment);
    // postService.addCommentToPost(comment.getPost(), comment);
    // userService.addCommentToUser(comment.getAuthor(), comment);
  }
}
