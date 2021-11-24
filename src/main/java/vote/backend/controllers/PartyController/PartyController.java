package vote.backend.controllers.PartyController;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import vote.backend.entities.Party.Party;
import vote.backend.services.PartyService.PartyServiceImpl;

@RestController
public class PartyController implements PartyControllerInterface {

  @Autowired
  PartyServiceImpl partyService;

  @Override
  public List<Party> findAllParties() {
    return partyService.findAll();
  }

  @Override
  public Party findPartyById(Long id) {
    return partyService.findPartyById(id);
  }
}
