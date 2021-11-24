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
  public void IncrementVoteCountByCandidateId(VoteRecord voteRecord, Long id) {
      VoteRecord VTC = voteRecordRepository
              .findByCandidateId(id)
              .orElseThrow(() -> new RuntimeException("Vote record with the candidate id" + id +"not found"));
      Long oldCount = VTC.getVoteCount();
      System.out.println(oldCount);
      Long newCount = VTC.incrementVoteCount(oldCount);
      System.out.println(newCount);
        VTC.setVoteCount(newCount);
          voteRecordRepository.save(VTC);
  }

  @Override
  public void addVoteRecord(VoteRecord voteRecord) { voteRecordRepository.save(voteRecord);}
}
