package vote.backend.services.UserService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vote.backend.entities.User.User;
import vote.backend.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  private String type = "User";

  @Override
  public List<User> findAllUsers() {
    return userRepository.findAll();
  }

  @Override
  public User findUserByEmail(String email) {
    return userRepository
      .findByEmail(email)
      .orElseThrow(
        () ->
          new RuntimeException(type + " with email: " + email + " not found")
      );
  }

  @Override
  public User findUserByNemId(Long id) {
    return userRepository
      .findByNemId(id)
      .orElseThrow(
        () -> new RuntimeException(type + " with nemId: " + id + " not found")
      );
  }

  @Override
  public void addUser(User user) {
    userRepository.save(user);
  }
}
