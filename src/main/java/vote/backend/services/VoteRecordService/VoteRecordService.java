package vote.backend.services.VoteRecordService;

import java.time.LocalDate;
import java.util.List;
import vote.backend.entities.VoteRecord.VoteRecord;

public interface VoteRecordService {
  public List<VoteRecord> findAllVoteRecords();

  public List<VoteRecord> findVoteRecordByDebateDate(LocalDate date);

  public VoteRecord findVoteRecordById(Long id);

  public VoteRecord findVoteRecordByCandidateId(Long id);

  public void incrementVoteCountByCandidateId(Long id);

  public void addVoteRecord(VoteRecord voteRecord);

  public void updateVoteRecord(VoteRecord voteRecord, Long id);
}
