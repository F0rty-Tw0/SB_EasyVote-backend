package vote.backend.controllers.VoteRecordController;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import vote.backend.entities.VoteRecord.VoteRecord;
import vote.backend.services.VoteRecordService.VoteRecordServiceImpl;

@RestController
public class VoteRecordController implements VoteRecordControllerInterface {

  @Autowired
  VoteRecordServiceImpl voteRecordService;

  @Override
  public List<VoteRecord> findAllVoteRecords() {
    return voteRecordService.findAllVoteRecords();
  }

  @Override
  public VoteRecord findVoteRecordById(Long id) {
    return voteRecordService.findVoteRecordById(id);
  }

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
  public void IncrementVoteCountByCandidateId(Long id) {}
}
