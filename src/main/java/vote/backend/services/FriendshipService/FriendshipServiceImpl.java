package vote.backend.services.FriendshipService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vote.backend.entities.Friendship.Friendship;
import vote.backend.repositories.FriendshipRepository;
import vote.backend.errorHandler.Exceptions.ResourceNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class FriendshipServiceImpl implements FriendshipService {

  @Autowired
  private FriendshipRepository friendshipRepository;

  @Override
  public List<Friendship> findAllFriendships() {
    return friendshipRepository.findAll();
  }

  @Override
  public List<Friendship> findFriendshipsByEmail(String email) {
    return friendshipRepository.findByEmail1(email);
  }

  @Override
  public Optional <Friendship> findFriendShipByUser1AndUser2(String email1, String email2) {
    return friendshipRepository.findByEmail1AndEmail2(email1, email2);
  }

  @Override
  public boolean ifFriendshipExists(String email1, String email2) {
    if (findFriendShipByUser1AndUser2(email1, email2).isEmpty() && findFriendShipByUser1AndUser2(email2, email1).isEmpty()) {
      return false;
    } else {
      return true;
    }
  }


  @Override
  public void addFriendship(Friendship friendship) {
    if (ifFriendshipExists(friendship.getEmail1(), friendship.getEmail2()) == false) {
      friendshipRepository.save(friendship);
    } else {
      System.out.println("Friendship already exists");
    }
  }
}
