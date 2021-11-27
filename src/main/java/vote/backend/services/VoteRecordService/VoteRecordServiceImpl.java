package vote.backend.services.VoteRecordService;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vote.backend.entities.VoteRecord.VoteRecord;
import vote.backend.repositories.VoteRecordRepository;

@Service
public class VoteRecordServiceImpl implements VoteRecordService {

  @Autowired
  private VoteRecordRepository voteRecordRepository;

  @Override
  public List<VoteRecord> findAllVoteRecords() {
    return voteRecordRepository.findAll();
  }

  @Override
  public List<VoteRecord> findVoteRecordByDebateDate(LocalDate date) {
    return voteRecordRepository.findByDebateDate(date);
  }

  @Override
  public VoteRecord findVoteRecordById(Long id) {
    return voteRecordRepository
      .findById(id)
      .orElseThrow(
        () -> new RuntimeException("Vote record with the id" + id + "not found")
      );
  }

  @Override
  public VoteRecord findVoteRecordByCandidateId(Long id) {
    return voteRecordRepository
      .findByCandidateId(id)
      .orElseThrow(
        () -> new RuntimeException("Vote record with the id" + id + "not found")
      );
  }

  @Override
  public void incrementVoteCountByCandidateId(Long id) {
    VoteRecord VTC = voteRecordRepository
      .findByCandidateId(id)
      .orElseThrow(
        () ->
          new RuntimeException(
            "Vote record with the candidate id" + id + "not found"
          )
      );

    int newCount = VTC.getVoteCount() + 1;
    VoteRecord newVote = new VoteRecord();
    newVote.setVoteCount(newCount);

    newVote.setCandidate(VTC.getCandidate());
    newVote.setDebateDate(VTC.getDebateDate());
    updateVoteRecord(newVote, id);
  }

  @Override
  public void addVoteRecord(VoteRecord voteRecord) {
    voteRecordRepository.save(voteRecord);
  }

  @Override
  public void updateVoteRecord(VoteRecord voteRecord, Long id) {
    VoteRecord voteRecordToUpdate = voteRecordRepository
      .findById(id)
      .orElseThrow(
        () ->
          new RuntimeException(
            "Vote record with the candidate id" + id + "not found"
          )
      );
    voteRecordToUpdate.setVoteCount(voteRecord.getVoteCount());
    voteRecordToUpdate.setCandidate(voteRecord.getCandidate());
    voteRecordToUpdate.setDebateDate(voteRecord.getDebateDate());
    voteRecordRepository.save(voteRecordToUpdate);
  }
}
