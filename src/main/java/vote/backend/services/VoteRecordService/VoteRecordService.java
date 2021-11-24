package vote.backend.services.VoteRecordService;

import vote.backend.entities.VoteRecord.VoteRecord;

import java.time.LocalDate;
import java.util.List;

public interface VoteRecordService {
  public List<VoteRecord> findAllVoteRecords();

  public VoteRecord findVoteRecordById(Long id);

  public VoteRecord findVoteRecordByCandidateId(Long id);

  public List<VoteRecord> findVoteRecordByDebateDate(LocalDate date);

  public void IncrementVoteCountByCandidateId(Long id);

  public void addVoteRecord(VoteRecord voteRecord);

  public void editVoteRecord(VoteRecord voteRecord, Long id);
}
