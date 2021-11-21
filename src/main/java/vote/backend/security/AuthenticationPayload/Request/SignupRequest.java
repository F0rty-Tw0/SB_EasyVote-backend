package vote.backend.security.AuthenticationPayload.Request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {

  @NotBlank
  @Size(max = 120)
  private String username;

  @NotBlank
  @Size(min = 4, max = 50)
  private String password;
}
