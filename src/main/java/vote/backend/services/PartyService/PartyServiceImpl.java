package vote.backend.services.PartyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vote.backend.entities.Party.Party;
import vote.backend.repositories.PartyRepository;

import java.util.List;

@Service
public class PartyServiceImpl implements PartyService {

  @Autowired
  private PartyRepository partyRepository;


  @Override
  public List<Party> findAll() { return partyRepository.findAll(); }

  @Override
  public Party findPartyById(Long id) {
    return partyRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Party with the id" + id + "not found"));
  }

  @Override
  public void addParty(Party party) { partyRepository.save(party); }

  @Override
  public void deletePartyById(Long id) {

  }
}
