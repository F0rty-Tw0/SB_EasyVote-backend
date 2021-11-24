package vote.backend.services.NemService;

import java.util.List;
import vote.backend.entities.User.Nem.Nem;

public interface NemService {
  public List<Nem> findAllNems();

  public Nem findNemByUsername(String username);

  public Boolean nemExistsByUsername(String username);

  public void addNem(Nem user);
}
