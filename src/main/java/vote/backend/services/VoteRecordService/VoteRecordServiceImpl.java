package vote.backend.services.VoteRecordService;

import org.springframework.beans.factory.annotation.Autowired;
import vote.backend.entities.VoteRecord.VoteRecord;
import vote.backend.repositories.VoteRecordRepository;

import java.time.LocalDate;
import java.util.List;

public class VoteRecordServiceImpl implements VoteRecordService {

  @Autowired
  private VoteRecordRepository voteRecordRepository;

  @Override
  public List<VoteRecord> findAllVoteRecords() {
    return voteRecordRepository.findAll();
  }

  @Override
  public VoteRecord findVoteRecordById(Long id) {
    return voteRecordRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Vote record with the id" + id +"not found"));

  }

  @Override
  public VoteRecord findVoteRecordByCandidateId(Long id) {
    return voteRecordRepository
            .findByCandidateId(id)
            .orElseThrow(() -> new RuntimeException("Vote record with the id" + id +"not found"));
  }

  @Override
  public List<VoteRecord> findVoteRecordByDebateDate(LocalDate date) {
  return voteRecordRepository.findByDebateDate(date);
  }

  @Override
  public void IncrementVoteCountByCandidateId(Long id) {
      VoteRecord VTC = voteRecordRepository
              .findByCandidateId(id)
              .orElseThrow(() -> new RuntimeException("Vote record with the candidate id" + id +"not found"));
        VTC.setVoteCount(VTC.getVoteCount()+1);
  }

  @Override
  public void addVoteRecord(VoteRecord voteRecord) { voteRecordRepository.save(voteRecord);}
}
