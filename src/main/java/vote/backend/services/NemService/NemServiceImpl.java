package vote.backend.services.NemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vote.backend.entities.User.Nem.Nem;
import vote.backend.repositories.NemRepository;

@Service
public class NemServiceImpl implements NemService {

  @Autowired
  private NemRepository nemRepository;

  private String type = "User";

  @Override
  public Nem findNemByUsername(String username) {
    return nemRepository
      .findByUsername(username)
      .orElseThrow(
        () ->
          new UsernameNotFoundException(
            type + " not found with the username: " + username
          )
      );
  }

  @Override
  public Boolean nemExistsByUsername(String username) {
    return nemRepository.existsByUsername(username);
  }

  @Override
  public void addNem(Nem nem) {
    nemRepository.save(nem);
  }
}
