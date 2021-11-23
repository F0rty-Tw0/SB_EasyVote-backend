package vote.backend.services.AuthService;

import org.springframework.http.ResponseEntity;
import vote.backend.security.AuthenticationPayload.Request.LoginRequest;
import vote.backend.security.AuthenticationPayload.Request.SignupRequest;
import vote.backend.security.AuthenticationPayload.Response.JwtResponse;

public interface AuthService {
  public ResponseEntity<?> registerNem(SignupRequest signUpRequest);

  public ResponseEntity<JwtResponse> authenticateUser(
    LoginRequest loginRequest
  );
}
