package vote.backend.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vote.backend.entities.Post.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
  public Optional<Post> findByTitle(String title);

  public List<Post> findByAuthorZip(String zip);
}
