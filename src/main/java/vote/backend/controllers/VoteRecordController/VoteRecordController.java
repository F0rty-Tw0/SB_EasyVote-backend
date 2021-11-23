package vote.backend.controllers.VoteRecordController;

import org.springframework.web.bind.annotation.RestController;
import vote.backend.entities.VoteRecord.VoteRecord;
import vote.backend.services.VoteRecordService.VoteRecordServiceImpl;

import java.time.LocalDate;
import java.util.List;

@RestController
public class VoteRecordController implements VoteRecordControllerInterface {

  VoteRecordServiceImpl voteRecordService;

  @Override
  public List<VoteRecord> findAllVoteRecords() { return voteRecordService.findAllVoteRecords(); }

  @Override
  public VoteRecord findVoteRecordById(Long id) { return voteRecordService.findVoteRecordById(id); }
//
//  @Override
//  public VoteRecord findVoteRecordByCandidateId(Long id) {
//    return null;
//  }

  @Override
  public VoteRecord findVoteRecordByDebateDate(LocalDate date) {
    return null;
  }

  @Override
  public void IncrementVoteCountByCandidateId(Long id) {

  }
}
