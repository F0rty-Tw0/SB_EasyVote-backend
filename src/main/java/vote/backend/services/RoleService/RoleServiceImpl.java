package vote.backend.services.RoleService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vote.backend.entities.User.Role.ERoles;
import vote.backend.entities.User.Role.Role;
import vote.backend.errorHandler.ErrorResponseCreator;
import vote.backend.errorHandler.Exceptions.ResourceNotFoundException;
import vote.backend.repositories.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

  @Autowired
  private RoleRepository roleRepository;

  private String object = "Role";

  @Override
  public List<Role> findAllRoles() {
    return roleRepository.findAll();
  }

  @Override
  public Role findRoleById(Long id) {
    return roleRepository
      .findById(id)
      .orElseThrow(
        () ->
          new ResourceNotFoundException(
            ErrorResponseCreator.notFoundException(object, "id", id)
          )
      );
  }

  @Override
  public void addRole(Role role) {
    roleRepository.save(role);
  }

  @Override
  public Role findRoleByName(String name) {
    return roleRepository
      .findByName(ERoles.valueOf(name))
      .orElseThrow(
        () ->
          new ResourceNotFoundException(
            ErrorResponseCreator.notFoundException(object, "name", name)
          )
      );
  }
}
