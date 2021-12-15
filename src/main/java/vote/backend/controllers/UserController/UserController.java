package vote.backend.controllers.UserController;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import vote.backend.entities.User.User;
import vote.backend.security.JWT.JwtUtils;
import vote.backend.services.UserService.UserService;

@RestController
public class UserController implements UserControllerInterface {

  @Autowired
  private UserService userService;

  @Autowired
  JwtUtils jwtUtils;

  @Override
  public List<User> findAllUsers() {
    return userService.findAllUsers();
  }

  @Override
  public Object getUserFromToken(String token) {
    String nemId = jwtUtils.getNemIdFromJwtToken(token.substring(7));
    return userService.findUserByNemId(Long.parseLong(nemId));
  }

  @Override
  public User findUserByNemId(Long id) {
    return userService.findUserByNemId(id);
  }

  @Override
  public void addUser(String token, User user) {
    String nemId = jwtUtils.getNemIdFromJwtToken(token.substring(7));
    User foundUser = userService.findUserByNemId(Long.parseLong(nemId));
    userService.updateUser(foundUser.getId(), user);
  }
}
