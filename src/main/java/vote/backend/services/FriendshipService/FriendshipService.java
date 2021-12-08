package vote.backend.services.FriendshipService;

import vote.backend.entities.Friendship.Friendship;

import java.util.List;

public interface FriendshipService {
  public List<Friendship> findAllFriendships();

  public List<Friendship> findFriendshipsByEmail(String email);

  public Friendship findFriendShipByUser1AndUser2(String email1, String email2);

  public boolean ifFriendshipExists(String email1, String email2);

  public void addFriendship(Friendship friendship);
}


