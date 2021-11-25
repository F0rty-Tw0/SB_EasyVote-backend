package vote.backend.services.UserService;

import java.util.List;
import vote.backend.entities.User.User;

public interface UserService {
  public List<User> findAllUsers();

  public User findUserByEmail(String email);

  public User findUserByNemId(Long id);

  public void convertUserToCandidate(User user);

  public void updateUser(Long id, User user);

  public void addUser(User user);

  public void deleteUserById(Long id);
}
