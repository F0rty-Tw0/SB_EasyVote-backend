package vote.backend.controllers.AuthController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import vote.backend.security.AuthenticationPayload.Request.LoginRequest;
import vote.backend.security.AuthenticationPayload.Request.SignupRequest;
import vote.backend.security.AuthenticationPayload.Response.JwtResponse;
import vote.backend.security.AuthenticationPayload.Response.MessageResponse;

@Api(
  tags = "Authentication",
  description = "- An endpoint for <b>Login</b> and <b>SignUp</b>"
)
@RequestMapping("/api/auth")
public interface AuthControllerInterface {
  @ApiOperation(
    value = " - Login with your Email and Password",
    notes = "After successful login, you will receive a signed <b>JWT</b>. Copy it and use it to authorize your account."
  )
  @PostMapping("/login")
  public ResponseEntity<JwtResponse> authenticateUser(
    @Valid @RequestBody LoginRequest loginRequest
  );

  @ApiOperation(
    value = " - Sign up a as a new User",
    notes = "In order to create a new <b>User</b>, you need an Email and Password. <em>(<b>Role</b> is optional)</em>."
  )
  @PostMapping("/signup")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<MessageResponse> registerUser(
    @Valid @RequestBody SignupRequest signUpRequest
  );
}
