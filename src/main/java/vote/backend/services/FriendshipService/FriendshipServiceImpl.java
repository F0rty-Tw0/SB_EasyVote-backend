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
  public List<Friendship> findFriendshipsByUser1(Long id) {
    return friendshipRepository.findByUser1Id(id);

  }

  @Override
  public Friendship findFriendShipByUser1AndUser2(Long id1, Long id2) {
    return findFriendShipByUser1AndUser2(id1, id2);
  }

  // TO DO
//  @Override
//  public boolean ifFriendshipExists(Long id1, Long id2) {
//    boolean exists = false;
//    if (findFriendShipByUser1AndUser2(id1, id2).getUser1().getId().equals(null) && findFriendShipByUser1AndUser2(id1, id2).getUser2().getId().equals(0)) {
//    exists = true;
//    };
//    return exists;
//  }


  @Override
  public void addFriendship(Friendship friendship) {
    friendshipRepository.save(friendship);
  }
}
