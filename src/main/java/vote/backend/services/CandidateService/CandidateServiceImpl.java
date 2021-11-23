package vote.backend.services.CandidateService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vote.backend.entities.User.Candidate.Candidate;
import vote.backend.repositories.CandidateRepository;

@Service
public class CandidateServiceImpl implements CandidateService {

  @Autowired
  private CandidateRepository candidateRepository;

  @Override
  public List<Candidate> findAllCandidates() {
    return candidateRepository.findAll();
  }

  @Override
  public Candidate findCandidateById(Long id) {
    return candidateRepository
      .findById(id)
      .orElseThrow(() -> new RuntimeException("Candidate with id" + id + "not found"));
  }

  @Override
  public void addCandidate(Candidate candidate) { candidateRepository.save(candidate); }

  @Override
  public void updateCandidateById(Long id) {}

  @Override
  public void deleteCandidateById(Long id) {}


}

