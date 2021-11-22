package vote.backend.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import vote.backend.entities.User.Roles.ERoles;
import vote.backend.entities.User.Roles.Role;
import vote.backend.repositories.MunicipalityRepository;
import vote.backend.repositories.NemRepository;
import vote.backend.repositories.RoleRepository;
import vote.backend.repositories.UserRepository;
import vote.backend.security.AuthenticationPayload.Request.SignupRequest;
import vote.backend.services.AuthService.AuthService;

@Component
@Profile("!test")
public class DatabaseConfig implements CommandLineRunner {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private AuthService authService;

  @Autowired
  private NemRepository nemRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private MunicipalityRepository municipalityRepository;

  @Override
  public void run(String... args) throws Exception {
    System.out.println("config runs");

    if (roleRepository.findAll().isEmpty()) {
      ERoles[] roles = ERoles.values();
      for (int i = 0; i < roles.length; i++) {
        roleRepository.save(new Role(roles[i]));
      }
    }

    if (nemRepository.findAll().isEmpty()) {
      authService.registerNem(new SignupRequest("admin", "test"));
    }
  }
}
