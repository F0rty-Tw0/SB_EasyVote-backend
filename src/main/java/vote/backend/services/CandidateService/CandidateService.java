package vote.backend.services.CandidateService;

import java.util.List;
import vote.backend.entities.Party.Party;
import vote.backend.entities.User.Candidate.Candidate;
import vote.backend.entities.User.Roles.Role;

public interface CandidateService {
  public List<Candidate> findAll();

  public Candidate findCandidateById(Long id);

  public void updateCandidateById(Long id);

  public void updateCandidatePartyById(Long id, Party party);

  public void updateCandidateRoleById(Long id, Role role);

  public void deleteCandidateById(Long id);

  public void addCandidate(Candidate candidate);
}
