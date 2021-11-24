package vote.backend.services.PartyService;

import java.util.List;
import vote.backend.entities.Party.Party;

public interface PartyService {
  public List<Party> findAllParties();

  public Party findPartyById(Long id);

  public Party findPartyByName(String name);

  public void addParty(Party party);

  public void deletePartyById(Long id);
}
