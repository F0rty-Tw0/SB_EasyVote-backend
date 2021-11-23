package vote.backend.controllers.CandidateController;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import vote.backend.entities.User.Candidate.Candidate;
import vote.backend.services.CandidateService.CandidateServiceImpl;

@RestController
public class CandidateController implements CandidateControllerInterface {

  CandidateServiceImpl candidateService;

  @Override
  public List<Candidate> findAllCandidates() {
    return candidateService.findAllCandidates();
  }

  @Override
  public Candidate findCandidateById(Long id) {
    return candidateService.findCandidateById(id);
  }
}
