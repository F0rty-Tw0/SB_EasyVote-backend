package vote.backend.controllers.CandidateController;

import org.springframework.beans.factory.annotation.Autowired;
import vote.backend.entities.User.Candidate.Candidate;
import vote.backend.services.CandidateService.CandidateServiceImpl;

import java.util.List;

public class CandidateController implements CandidateControllerInterface{

  @Autowired
  CandidateServiceImpl candidateService;


  @Override
  public List<Candidate> findAllCandidates() {
    return candidateService.findAllCandidates();
  }

  @Override
  public Candidate findCandidateById(Long id) { return candidateService.findCandidateById(id); }

}
