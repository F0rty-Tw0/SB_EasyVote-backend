package vote.backend.services.PartyService;

import org.springframework.beans.factory.annotation.Autowired;
import vote.backend.entities.Party.Party;
import vote.backend.repositories.PartyRepository;

import java.util.List;

public class PartyServiceImpl implements PartyService {

  @Autowired
  private PartyRepository partyRepository;


  @Override
  public List<Party> findAllParties() { return partyRepository.findAll(); }

  @Override
  public Party findPartyById(Long id) {
    return partyRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Party with the id" + id + "not found"));
  }

  @Override
  public void deletePartyById(Long id) {

  }
}
