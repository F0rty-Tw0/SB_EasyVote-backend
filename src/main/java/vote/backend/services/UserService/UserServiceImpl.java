package vote.backend.services.UserService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vote.backend.entities.Post.Comment.Comment;
import vote.backend.entities.Post.Post;
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
  public void convertUserToCandidate(Long id) {
    userRepository.convertUserToCandidate(id);
  }

  @Override
  public void updateUser(Long id, User user) {
    User foundUser = userRepository
      .findById(id)
      .orElseThrow(
        () -> new RuntimeException(type + " with id: " + id + " not found")
      );
    foundUser.setName(user.getName());
    foundUser.setCpr(user.getCpr());
    foundUser.setEmail(user.getEmail());
    foundUser.setPhoneNumber(user.getPhoneNumber());
    foundUser.setAddress(user.getAddress());
    foundUser.setBirthDate(user.getBirthDate());
    foundUser.setZip(user.getZip());
    foundUser.setMunicipality(user.getMunicipality());

    userRepository.save(user);
  }

  @Override
  public void addPostToUser(User user, Post post) {
    user.addPost(post);
    userRepository.save(user);
  }

  @Override
  public void addCommentToUser(User author, Comment comment) {
    User foundUser = userRepository
      .findById(author.getId())
      .orElseThrow(
        () ->
          new RuntimeException(
            type + " with id: " + author.getId() + " not found"
          )
      );

    foundUser.addComment(comment);
    userRepository.save(foundUser);
  }

  @Override
  public void addUser(User user) {
    userRepository.save(user);
  }

  @Override
  public void deleteUserById(Long id) {
    userRepository.deleteById(id);
  }
}
