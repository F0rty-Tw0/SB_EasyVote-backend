package vote.backend.services.FriendshipService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vote.backend.entities.Friendship.Friendship;
import vote.backend.repositories.FriendshipRepository;

import java.util.List;

@Service
public class FriendshipServiceImpl implements FriendshipService{

  @Autowired
  private FriendshipRepository friendshipRepository;

  @Override
  public List<Friendship> findAllFriendships() {
    return friendshipRepository.findAll();
  }

  @Override
  public Friendship findFriendshipsByUser1(Long id) {
    return null;
  }

  @Override
  public Friendship findFriendshipsByUser2(Long id) {
    return null;
  }

  @Override
  public Friendship findFriendShipByUser1AndUser2(Long id1, Long id2) {
    return null;
  }

  @Override
  public void addFriendship(Friendship friendship) {
    friendshipRepository.save(friendship);
  }
}
