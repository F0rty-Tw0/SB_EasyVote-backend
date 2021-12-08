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
}
