package vote.backend.services.VoteRecordService;

import vote.backend.entities.VoteRecord.VoteRecord;

import java.util.List;

public interface VoteRecordService {
  public List<VoteRecord> findAllVoteRecords();

  public VoteRecord findVoteRecordById(Long id);

  public VoteRecord findVoteRecordByCandidateId(Long id);


}
