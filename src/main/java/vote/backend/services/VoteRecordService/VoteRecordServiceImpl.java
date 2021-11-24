package vote.backend.services.VoteRecordService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vote.backend.entities.VoteRecord.VoteRecord;
import vote.backend.repositories.VoteRecordRepository;

import java.time.LocalDate;
import java.util.List;

@Service
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
  public void editVoteRecord(VoteRecord voteRecord, Long id) {
    VoteRecord oldRecord = voteRecordRepository.findById(id).orElseThrow(() -> new RuntimeException("Vote record with the candidate id" + id +"not found"));
    oldRecord.setVoteCount(voteRecord.getVoteCount());
    oldRecord.setCandidate(voteRecord.getCandidate());
    oldRecord.setDebateDate(voteRecord.getDebateDate());
    voteRecordRepository.save(oldRecord);
  }

  @Override
  public void IncrementVoteCountByCandidateId(Long id) {
      VoteRecord VTC = voteRecordRepository
              .findByCandidateId(id)
              .orElseThrow(() -> new RuntimeException("Vote record with the candidate id" + id +"not found"));

      Long newCount = VTC.getVoteCount()+1;
      VoteRecord newVote = new VoteRecord();
      newVote.setVoteCount(newCount);

      newVote.setCandidate(VTC.getCandidate());
      newVote.setDebateDate(VTC.getDebateDate());
     editVoteRecord(newVote, id);
  }

  @Override
  public void addVoteRecord(VoteRecord voteRecord) { voteRecordRepository.save(voteRecord);}
}
