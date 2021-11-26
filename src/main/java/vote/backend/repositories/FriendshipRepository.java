package vote.backend.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import vote.backend.entities.Friendship.Friendship;

import java.util.List;
import java.util.Optional;

public interface FriendshipRepository extends JpaRepository <Friendship, Long> {
  public Optional<Friendship> findById(Long id);

  public List<Friendship> findByUser1Id(Long id);

  public Optional<Friendship> findByUser1IdAndUser2Id(Long id1, Long id2);

}
