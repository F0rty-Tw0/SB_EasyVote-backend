package vote.backend.controllers.NemController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import vote.backend.services.NemService.NemService;

@RestController
public class NemController implements NemControllerInterface {

  @Autowired
  private NemService nemService;

  @Override
  public Object findNemByUsername(String username) {
    return nemService.findNemByUsername(username);
  }

  @Override
  public Boolean nemExistsByUsername(String username) {
    return nemService.nemExistsByUsername(username);
  }
}
