package vote.backend.services.NemService;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collection;
import java.util.Collections;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import vote.backend.entities.User.Nem.Nem;

@EqualsAndHashCode
public class NemDetailsImpl implements UserDetails {

  private final Long id;
  private String username;

  @JsonIgnore
  private String password;

  public NemDetailsImpl(Long id, String username, String password) {
    this.id = id;
    this.username = username;
    this.password = password;
  }

  public static UserDetails build(Nem nem) {
    return new NemDetailsImpl(
      nem.getId(),
      nem.getUsername(),
      nem.getPassword()
    );
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.emptyList();
  }

  public Long getId() {
    return id;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
