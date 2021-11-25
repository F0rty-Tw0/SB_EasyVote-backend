package vote.backend.services.CommentService;

import vote.backend.entities.Post.Comment.Comment;

public interface CommentService {
  void addComment(Comment comment);
}
