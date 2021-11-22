package vote.backend.controllers.CandidateController;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import vote.backend.entities.Party.Party;
import vote.backend.entities.User.Candidate.Candidate;
import vote.backend.services.CandidateService.CandidateServiceImpl;

public class CandidateController implements CandidateControllerInterface {

  @Autowired
  CandidateServiceImpl candidateService;

  @Override
  public List<Candidate> findAllCandidates() {
    return candidateService.findAllCandidates();
  }

  @Override
  public Party findCandidateById(Long id) {
    // I'm getting an error with the Optional here, need to fix for later
    return null;
  }
}
