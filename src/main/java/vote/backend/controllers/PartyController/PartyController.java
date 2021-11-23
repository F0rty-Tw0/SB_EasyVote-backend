package vote.backend.controllers.PartyController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import vote.backend.entities.Party.Party;
import vote.backend.services.PartyService.PartyService;

import java.util.List;

@RestController
public class PartyController implements PartyControllerInterface {

  @Autowired
  PartyService partyService;


  @Override
  public List<Party> findAllParties() { return partyService.findAllParties();}

  @Override
  public Party findPartyById(Long id) { return partyService.findPartyById(id); }

}
