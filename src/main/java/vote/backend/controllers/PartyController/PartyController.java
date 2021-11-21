package vote.backend.controllers.PartyController;

import org.springframework.beans.factory.annotation.Autowired;
import vote.backend.entities.Party.Party;
import vote.backend.services.PartyService.PartyServiceImpl;

import java.util.List;

public class PartyController implements PartyControllerInterface {

  @Autowired
  PartyServiceImpl partyService;


  @Override
  public List<Party> findAllParties() {
    return partyService.findAllParties();
  }

  @Override
  public Party findPartyById(Long id) {
    // I'm getting an error with the Optional here, need to fix for later
    //    return partyService.findPartyById(id);
    return null;
  }
}
