package vote.backend.services.NemService;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import vote.backend.entities.User.Nem.Nem;

@EqualsAndHashCode
public class NemDetailsImpl implements UserDetails {

  private final Long id;
  private String cpr;

  @JsonIgnore
  private String password;

  private Collection<? extends GrantedAuthority> grantedAuthorities;

  public NemDetailsImpl(
    Long id,
    String cpr,
    String password,
    Collection<? extends GrantedAuthority> grantedAuthorities
  ) {
    this.id = id;
    this.cpr = cpr;
    this.password = password;
    this.grantedAuthorities = grantedAuthorities;
  }

  public static UserDetails build(Nem nem) {
    List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
    grantedAuthorities.add(
      new SimpleGrantedAuthority(nem.getRole().getName().name())
    );
    return new NemDetailsImpl(
      nem.getId(),
      nem.getCpr(),
      nem.getPassword(),
      grantedAuthorities
    );
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return grantedAuthorities;
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
    return cpr;
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
