package vote.backend.services.PartyService;

import org.springframework.beans.factory.annotation.Autowired;
import vote.backend.entities.Party.Party;
import vote.backend.repositories.PartyRepository;

import java.util.List;
import java.util.Optional;

public class PartyServiceImpl implements PartyService {

  @Autowired
  private PartyRepository partyRepository;


  @Override
  public List<Party> findAllParties() {
    List<Party> parties = partyRepository.findAll();
    return parties;
  }

  @Override
  public Optional<Party> findPartyById(Long id) {
    Optional<Party> party = partyRepository.findById(id);
    return party;
  }

  @Override
  public void deletePartyById(Long id) {

  }
}
