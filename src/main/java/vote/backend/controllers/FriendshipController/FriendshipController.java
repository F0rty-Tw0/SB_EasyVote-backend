package vote.backend.controllers.FriendshipController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import vote.backend.entities.Friendship.Friendship;
import vote.backend.services.FriendshipService.FriendshipService;

import java.util.List;

@RestController
public class FriendshipController implements FriendshipControllerInterface {

  @Autowired
  FriendshipService friendshipService;

  @Override
  public List<Friendship> findAllFriendships() {
    return friendshipService.findAllFriendships();
  }

  @Override
  public List<Friendship> findFriendshipsByEmail(String email) {
    return friendshipService.findFriendshipsByEmail(email);
  }

  @Override
  public Object findFriendShipByUser1AndUser2(String email1, String email2) {
    return friendshipService.findFriendShipByUser1AndUser2(email1, email2);
  }

  @Override
  public void addFriendship(Friendship friendship) {
      friendshipService.addFriendship(friendship); }
}
