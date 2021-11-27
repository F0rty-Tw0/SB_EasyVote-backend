package vote.backend.services.CommentService;

import vote.backend.entities.Post.Comment.Comment;

public interface CommentService {
  void updateCommentById(Comment comment, Long id);
  void addComment(Comment comment);
  void deleteCommentById(Comment comment, Long id);
}
