package vote.backend.services.PartyService;

import vote.backend.entities.Party.Party;

import java.util.List;
import java.util.Optional;

public interface PartyService {
  public List<Party> findAllParties();

  public Optional<Party> findPartyById(Long id);

  public void deletePartyById(Long id);
}
