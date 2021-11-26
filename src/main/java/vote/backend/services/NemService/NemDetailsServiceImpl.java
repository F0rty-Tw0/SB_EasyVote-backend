package vote.backend.services.NemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vote.backend.ErrorHandler.ErrorResponseCreator;
import vote.backend.ErrorHandler.Exceptions.ResourceNotFoundException;
import vote.backend.entities.User.Nem.Nem;
import vote.backend.repositories.NemRepository;

@Service
public class NemDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private NemRepository nemRepository;

  String object = "Username";

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) {
    Nem nem = nemRepository
      .findByUsername(username)
      .orElseThrow(
        () -> new ResourceNotFoundException(
          ErrorResponseCreator.NotFoundException(object, "name", username)
        )
      );
    return NemDetailsImpl.build(nem);
  }
}
