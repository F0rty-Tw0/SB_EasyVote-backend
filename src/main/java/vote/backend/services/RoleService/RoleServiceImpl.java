package vote.backend.services.RoleService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vote.backend.entities.User.Role.Role;
import vote.backend.repositories.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

  @Autowired
  private RoleRepository roleRepository;

  private String type = "Role";

  @Override
  public List<Role> findAllRoles() {
    return roleRepository.findAll();
  }

  @Override
  public Role findRoleById(Long id) {
    return roleRepository
      .findById(id)
      .orElseThrow(
        () -> new RuntimeException(type + " with id: " + id + "not found")
      );
  }

  @Override
  public void addRole(Role role) {
    roleRepository.save(role);
  }

  @Override
  public Role findRoleByName(String name) {
    // TODO Auto-generated method stub
    return null;
  }
}
