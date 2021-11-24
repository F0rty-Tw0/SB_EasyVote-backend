package vote.backend.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import vote.backend.entities.User.User;

public interface UserRepository extends JpaRepository<User, Long> {
  public Optional<User> findByEmail(String email);

  public Optional<User> findByNemId(Long id);

  public Boolean existsByNemId(Long nemId);

  @Modifying
  @Transactional
  @Query("Update User SET dType = Candidate WHERE id=:id")
  public void convertUserToCandidate(Long id);
}
