package vote.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vote.backend.entities.User.User;

public interface UserRepository extends JpaRepository<User, Long> {
  public Boolean existsByNemId(Long nemId);
}
