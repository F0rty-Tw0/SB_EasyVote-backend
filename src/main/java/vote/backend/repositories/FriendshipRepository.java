package vote.backend.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import vote.backend.entities.Friendship.Friendship;

import java.util.List;
import java.util.Optional;

public interface FriendshipRepository extends JpaRepository <Friendship, Long> {
  public Optional<Friendship> findById(Long id);

  public List<Friendship> findByEmail1(String email1);

  public Optional<Friendship> findByEmail1AndEmail2(String email1, String email2);

}
