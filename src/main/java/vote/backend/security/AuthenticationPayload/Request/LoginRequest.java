package vote.backend.security.AuthenticationPayload.Request;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class LoginRequest {

  @NotBlank
  private String username;

  @NotBlank
  private String password;
}
