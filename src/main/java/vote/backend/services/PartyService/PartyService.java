package vote.backend.services.PartyService;

import vote.backend.entities.Party.Party;

import java.util.List;

public interface PartyService {
  public List<Party> findAllParties();

  public Party findPartyById(Long id);

  public void deletePartyById(Long id);
}
