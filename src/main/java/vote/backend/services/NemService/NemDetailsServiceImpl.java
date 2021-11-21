package vote.backend.services.NemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vote.backend.entities.User.Nem.Nem;
import vote.backend.repositories.NemRepository;

@Service
public class NemDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private NemRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String cpr)
    throws UsernameNotFoundException {
    Nem nem = userRepository
      .findByCpr(cpr)
      .orElseThrow(
        () ->
          new UsernameNotFoundException("User not found with the cpr: " + cpr)
      );
    return NemDetailsImpl.build(nem);
  }
}
