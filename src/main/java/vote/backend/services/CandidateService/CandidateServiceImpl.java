package vote.backend.services.CandidateService;

import org.springframework.beans.factory.annotation.Autowired;
import vote.backend.entities.User.Candidate.Candidate;
import vote.backend.repositories.CandidateRepository;

import java.util.List;
import java.util.Optional;

public class CandidateServiceImpl implements CandidateService {

  @Autowired
  private CandidateRepository candidateRepository;


  @Override
  public List<Candidate> findAllCandidates() {
    List<Candidate> candidates= candidateRepository.findAll();
    return candidates;
  }

  @Override
  public Optional<Candidate> findCandidateById(Long id) {
    Optional<Candidate> candidate = candidateRepository.findById(id);
    return candidate;
  }

  @Override
  public void updateCandidateById(Long id) {

  }

  @Override
  public void deleteCandidateById(Long id) {

  }
}
