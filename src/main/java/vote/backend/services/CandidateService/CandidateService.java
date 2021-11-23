package vote.backend.services.CandidateService;

import vote.backend.entities.Party.Party;
import vote.backend.entities.User.Candidate.Candidate;
import vote.backend.entities.User.Roles.Role;
import vote.backend.entities.User.User;

import java.util.List;

public interface CandidateService {
  public List<Candidate> findAllCandidates();

  public Candidate findCandidateById(Long id);

  public void updateCandidateById(Long id);

//  public void addCandidate(CandidateDTO candidate);

  public void updateCandidatePartyById(Long id, Party party);

  public void updateCandidateRoleById(Long id, Role role);

  public void deleteCandidateById(Long id);

  public void addCandidate(Candidate candidate);

}
