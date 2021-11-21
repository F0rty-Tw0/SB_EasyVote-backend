package vote.backend.services.NemService;

import vote.backend.entities.User.Nem.Nem;

public interface NemService {
  public void addNem(Nem user);

  public Nem findNemByUsername(String username);

  public Boolean nemExistsByUsername(String username);
}
