package vote.backend.services.AuthService;

import javax.management.relation.RoleNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vote.backend.entities.User.Nem.Nem;
import vote.backend.entities.User.Role.ERoles;
import vote.backend.entities.User.Role.Role;
import vote.backend.ErrorHandler.Exceptions.ResourceNotFoundException;
import vote.backend.entities.User.User;
import vote.backend.repositories.NemRepository;
import vote.backend.repositories.RoleRepository;
import vote.backend.repositories.UserRepository;
import vote.backend.security.AuthenticationPayload.Request.LoginRequest;
import vote.backend.security.AuthenticationPayload.Request.SignupRequest;
import vote.backend.security.AuthenticationPayload.Response.JwtResponse;
import vote.backend.security.AuthenticationPayload.Response.MessageResponse;
import vote.backend.security.JWT.JwtUtils;
import vote.backend.services.NemService.NemDetailsImpl;

@Service
public class AuthServiceImpl implements AuthService {

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private NemRepository nemRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  JwtUtils jwtUtils;

  private String message = " Error: Role is not found.";

  @Override
  public ResponseEntity<MessageResponse> registerNem(
    SignupRequest signUpRequest
  ) {
    boolean nemExists = nemRepository.existsByUsername(
      signUpRequest.getUsername()
    );
    if (nemExists) {
      return ResponseEntity
        .badRequest()
        .body(new MessageResponse("Error: Username is already in use!"));
    }

    Nem nem = new Nem(
      passwordEncoder.encode(signUpRequest.getPassword()),
      signUpRequest.getUsername()
    );

    nemRepository.save(nem);

    return ResponseEntity.ok(
      new MessageResponse("User registered successfully!")
    );
  }

  @Override
  public ResponseEntity<JwtResponse> authenticateUser(
    LoginRequest loginRequest
  ) {
    Authentication authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        loginRequest.getUsername(),
        loginRequest.getPassword()
      )
    );
    User user = new User();
    Role voterRole = roleRepository
      .findByName(ERoles.ROLE_VOTER)
      .orElseThrow(() -> new ResourceNotFoundException("Error: Role is not found"));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    NemDetailsImpl nemDetails = (NemDetailsImpl) authentication.getPrincipal();

    user.setRole(voterRole);

    Nem nem = nemRepository
      .findById(nemDetails.getId())
      .orElseThrow(() -> new ResourceNotFoundException("Error: Nem is not found."));

    user.setNem(nem);

    if (Boolean.FALSE.equals(userRepository.existsByNemId(nem.getId()))) {
      userRepository.save(user);
    }

    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(
      voterRole.getName().name()
    );

    String role = grantedAuthority.getAuthority();

    return ResponseEntity.ok(
      new JwtResponse(jwt, nemDetails.getId(), nemDetails.getUsername(), role)
    );
  }
}
