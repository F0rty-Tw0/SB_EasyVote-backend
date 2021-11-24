package vote.backend.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vote.backend.entities.User.Candidate.Candidate;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
  public Optional<Candidate> findById(Long id);

  public Optional<Candidate> findByPartyId(Long partyId);
}
