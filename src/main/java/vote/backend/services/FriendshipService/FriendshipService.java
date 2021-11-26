package vote.backend.services.FriendshipService;

import vote.backend.entities.Friendship.Friendship;

import java.util.List;

public interface FriendshipService {
  public List<Friendship> findAllFriendships();

  public List<Friendship> findFriendshipsByUser1(Long id);

  public Friendship findFriendShipByUser1AndUser2(Long id1, Long id2);

  // TO DO
//  public boolean ifFriendshipExists(Long id1, Long id2);

  public void addFriendship(Friendship friendship);
}


