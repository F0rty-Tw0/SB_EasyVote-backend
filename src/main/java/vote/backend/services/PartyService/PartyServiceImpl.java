package vote.backend.services.PartyService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vote.backend.entities.Party.Party;
import vote.backend.entities.User.Candidate.Candidate;
import vote.backend.repositories.PartyRepository;

@Service
public class PartyServiceImpl implements PartyService {

  @Autowired
  private PartyRepository partyRepository;

  @Override
  public List<Party> findAllParties() {
    return partyRepository.findAll();
  }

  @Override
  public Party findPartyById(Long id) {
    return partyRepository
      .findById(id)
      .orElseThrow(
        () -> new RuntimeException("Party with the id " + id + "not found")
      );
  }

  @Override
  public Party findPartyByName(String name) {
    return partyRepository
      .findByName(name)
      .orElseThrow(
        () -> new RuntimeException("Party with the name " + name + "not found")
      );
  }

  @Override
  public void addParty(Party party) {
    partyRepository.save(party);
  }

  @Override
  public void deletePartyById(Long id) {
    partyRepository.deleteById(id);
  }

  @Override
  public void addCandidateToParty(Party party, Candidate candidate) {
    party.addCandidate(candidate);
    partyRepository.save(party);
  }
}
