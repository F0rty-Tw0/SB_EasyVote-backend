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
  private String role;

  public JwtResponse(
    String accessToken,
    Long id,
    String username,
    String role
  ) {
    this.accessToken = accessToken;
    this.id = id;
    this.username = username;
    this.role = role;
  }
}
