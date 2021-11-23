package vote.backend.services.PartyService;

import vote.backend.entities.Party.Party;

import java.util.List;


public interface PartyService {
  public List<Party> findAll();

  public Party findPartyById(Long id);

  public void addParty(Party party);

  public void deletePartyById(Long id);
}
