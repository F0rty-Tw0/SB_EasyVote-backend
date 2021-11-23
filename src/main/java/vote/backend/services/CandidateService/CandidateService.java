package vote.backend.services.CandidateService;

import vote.backend.entities.User.Candidate.Candidate;

import java.util.List;

public interface CandidateService {
  public List<Candidate> findAllCandidates();

  public Candidate findCandidateById(Long id);

  public void updateCandidateById(Long id);

//  public void addCandidate(CandidateDTO candidate);

  public void deleteCandidateById(Long id);

  public void addCandidate(Candidate candidate);
}
