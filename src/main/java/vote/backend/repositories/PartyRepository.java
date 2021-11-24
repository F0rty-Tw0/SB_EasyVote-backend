package vote.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vote.backend.entities.Party.Party;

import java.util.Optional;

@Repository
public interface PartyRepository extends JpaRepository <Party, Long> {
  public Optional<Party> findById(Long id);
  public Optional<Party> findByName(String name);
}
