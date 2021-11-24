package vote.backend.services.RoleService;

import java.util.List;
import vote.backend.entities.User.Role.Role;

public interface RoleService {
  public List<Role> findAllRoles();

  public Role findRoleById(Long id);

  public Role findRoleByName(String name);

  public void addRole(Role role);
}
