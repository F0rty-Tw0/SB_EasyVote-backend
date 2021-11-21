package vote.backend.controllers.AuthController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vote.backend.security.AuthenticationPayload.Request.LoginRequest;
import vote.backend.security.AuthenticationPayload.Request.SignupRequest;
import vote.backend.security.AuthenticationPayload.Response.JwtResponse;
import vote.backend.services.AuthService.AuthServiceImpl;

@RestController
public class AuthController implements AuthControllerInterface {

  @Autowired
  AuthServiceImpl authService;

  @Override
  public ResponseEntity<JwtResponse> authenticateUser(
    LoginRequest loginRequest
  ) {
    return authService.authenticateUser(loginRequest);
  }

  @Override
  public ResponseEntity<?> registerUser(SignupRequest signUpRequest) {
    try {
      return authService.registerUser(signUpRequest);
    } catch (Exception e) {
      System.out.println("error!!");
    }
    return authService.registerUser(signUpRequest);
  }
}
