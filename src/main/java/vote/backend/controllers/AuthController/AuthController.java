package vote.backend.controllers.AuthController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vote.backend.security.AuthenticationPayload.Request.LoginRequest;
import vote.backend.security.AuthenticationPayload.Request.SignupRequest;
import vote.backend.security.AuthenticationPayload.Response.JwtResponse;
import vote.backend.services.AuthService.AuthService;

@RestController
public class AuthController implements AuthControllerInterface {

  @Autowired
  private AuthService authService;

  @Override
  public ResponseEntity<JwtResponse> authenticateUser(
    LoginRequest loginRequest
  ) {
    return authService.authenticateUser(loginRequest);
  }

  @Override
  public ResponseEntity<?> registerUser(SignupRequest signUpRequest) {
    return authService.registerNem(signUpRequest);
  }
}
