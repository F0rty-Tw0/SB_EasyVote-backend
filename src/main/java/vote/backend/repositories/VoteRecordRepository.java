package vote.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vote.backend.entities.VoteRecord.VoteRecord;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface VoteRecordRepository extends JpaRepository<VoteRecord, Long> {
  public Optional<VoteRecord> findById(Long id);
  public List<VoteRecord> findByDebateDate(LocalDate date);
  public Optional<VoteRecord> findByCandidateId(Long id);
//  public void updateVoteCountByCandidateId(VoteRecord voteRecord, Long id);
}
