package vote.backend.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import vote.backend.entities.User.User;

public interface UserRepository extends JpaRepository<User, Long> {
  public Boolean existsByNemId(Long nemId);

  public Optional<User> findByEmail(String email);

  public Optional<User> findByNemId(Long id);
}
