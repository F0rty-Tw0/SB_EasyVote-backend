package vote.backend.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vote.backend.entities.Post.Comment.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
  public Optional<Comment> findById(Long id);
}
