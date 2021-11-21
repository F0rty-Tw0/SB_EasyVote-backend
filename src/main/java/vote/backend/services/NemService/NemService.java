package vote.backend.services.NemService;

import vote.backend.entities.User.Nem.Nem;

public interface NemService {
  public void addNem(Nem user);

  public Nem findNemByCpr(String cpr);

  public Boolean nemExistsByCpr(String cpr);
}
