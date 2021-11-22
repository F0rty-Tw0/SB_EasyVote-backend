package vote.backend.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vote.backend.entities.User.Nem.Nem;

@Repository
public interface NemRepository extends JpaRepository<Nem, Long> {
  public Optional<Nem> findByUsername(String username);

  public Boolean existsByUsername(String username);
}
