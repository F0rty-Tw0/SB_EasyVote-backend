package vote.backend.services.UserService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vote.backend.entities.User.Role.Role;
import vote.backend.entities.User.User;
import vote.backend.repositories.UserRepository;
import vote.backend.services.MunicipalityService.MunicipalityService;
import vote.backend.services.RoleService.RoleService;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private MunicipalityService municipalityService;

  @Autowired
  private RoleService roleService;

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
  public void convertUserToCandidate(User user) {
    Role role = roleService.findRoleByName("ROLE_CANDIDATE");
    user.setRole(role);
    userRepository.save(user);
    userRepository.convertUserToCandidate(user.getId());
  }

  @Override
  public void updateUser(Long id, User user) {
    User userToUpdate = userRepository
      .findById(id)
      .orElseThrow(
        () -> new RuntimeException(type + " with id: " + id + " not found")
      );
    userToUpdate.setName(user.getName());
    userToUpdate.setCpr(user.getCpr());
    userToUpdate.setEmail(user.getEmail());
    userToUpdate.setPhoneNumber(user.getPhoneNumber());
    userToUpdate.setAddress(user.getAddress());
    userToUpdate.setBirthDate(user.getBirthDate());
    if (user.getZip() != null) {
      userToUpdate.setZip(user.getZip());
      userToUpdate.setMunicipality(
        municipalityService.findMunicipalityByZipCode(
          Long.parseLong(user.getZip())
        )
      );
    }
    userRepository.save(userToUpdate);
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
