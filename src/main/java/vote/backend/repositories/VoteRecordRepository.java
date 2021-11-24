package vote.backend.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vote.backend.entities.VoteRecord.VoteRecord;

@Repository
public interface VoteRecordRepository extends JpaRepository<VoteRecord, Long> {
  public Optional<VoteRecord> findById(Long id);

  public Optional<VoteRecord> findByCandidateId(Long id);

  public List<VoteRecord> findByDebateDate(LocalDate date);
}
