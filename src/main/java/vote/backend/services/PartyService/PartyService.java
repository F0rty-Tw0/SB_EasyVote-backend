package vote.backend.services.PartyService;

import java.util.List;
import vote.backend.entities.Party.Party;
import vote.backend.entities.User.Candidate.Candidate;

public interface PartyService {
  public List<Party> findAllParties();

  public Party findPartyById(Long id);

  public Party findPartyByName(String name);

  public void addParty(Party party);

  public void deletePartyById(Long id);

  public void addCandidateToParty(Party party, Candidate candidate);
}
