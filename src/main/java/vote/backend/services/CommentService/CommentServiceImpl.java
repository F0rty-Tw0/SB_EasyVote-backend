package vote.backend.services.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vote.backend.entities.Post.Comment.Comment;
import vote.backend.repositories.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService {

  @Autowired
  private CommentRepository commentRepository;

  @Override
  public void addComment(Comment comment) {
    commentRepository.save(comment);
  }
}
