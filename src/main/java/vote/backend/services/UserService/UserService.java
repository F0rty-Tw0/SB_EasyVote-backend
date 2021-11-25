package vote.backend.services.UserService;

import java.util.List;
import vote.backend.entities.Post.Comment.Comment;
import vote.backend.entities.Post.Post;
import vote.backend.entities.User.User;

public interface UserService {
  public List<User> findAllUsers();

  public User findUserByEmail(String email);

  public User findUserByNemId(Long id);

  public void convertUserToCandidate(Long id);

  public void updateUser(Long id, User user);

  public void addPostToUser(User user, Post post);

  public void addCommentToUser(User user, Comment comment);

  public void addUser(User user);

  public void deleteUserById(Long id);
}
