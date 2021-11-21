package vote.backend.security.AuthenticationPayload.Response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JwtResponse {

  private String accessToken;
  private String tokenType = "Bearer";
  private Long id;
  private String username;

  public JwtResponse(String accessToken, Long id, String username) {
    this.accessToken = accessToken;
    this.id = id;
    this.username = username;
  }
}
