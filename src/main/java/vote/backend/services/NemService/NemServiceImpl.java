package vote.backend.services.NemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vote.backend.entities.User.Nem.Nem;
import vote.backend.repositories.NemRepository;

@Service
public class NemServiceImpl implements NemService {

  @Autowired
  private NemRepository nemRepository;

  private String type = "User";

  @Override
  public Nem findNemByCpr(String cpr) {
    return nemRepository.findByCpr(cpr).orElseThrow();
  }

  @Override
  public Boolean nemExistsByCpr(String cpr) {
    return nemRepository.existsByCpr(cpr);
  }

  @Override
  public void addNem(Nem nem) {
    nemRepository.save(nem);
  }
}
