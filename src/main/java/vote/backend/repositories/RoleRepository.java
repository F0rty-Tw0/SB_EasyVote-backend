package vote.backend.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vote.backend.entities.User.Roles.ERoles;
import vote.backend.entities.User.Roles.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  public Optional<Role> findByName(ERoles name);
}
