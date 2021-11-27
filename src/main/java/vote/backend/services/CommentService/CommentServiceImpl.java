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
  public void updateCommentById(Comment comment, Long id) {
    Comment commentToUpdate = commentRepository
      .findById(id)
      .orElseThrow(() -> new RuntimeException("Comment not found"));

    commentToUpdate.setText(comment.getText());
    commentToUpdate.setDateEdited(comment.getDateEdited());
    commentRepository.save(commentToUpdate);
  }

  @Override
  public void addComment(Comment comment) {
    commentRepository.save(comment);
  }

  @Override
  public void deleteCommentById(Comment comment, Long id) {
    Comment commentToDelete = commentRepository
      .findById(id)
      .orElseThrow(() -> new RuntimeException("Comment not found"));

    commentRepository.delete(commentToDelete);
  }
}
