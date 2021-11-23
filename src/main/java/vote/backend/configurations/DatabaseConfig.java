package vote.backend.configurations;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import vote.backend.entities.Municipality.Municipality;
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

    if (municipalityRepository.findAll().isEmpty()) {
      String url = "https://api.dataforsyningen.dk/kommuner";

      RestTemplate restTemplate = new RestTemplate();
      String result = restTemplate.getForObject(url, String.class);

      JSONArray jsonArray = new JSONArray(result);

      for (int i = 0; i < jsonArray.length(); i++) {
        JSONObject jsonObject = jsonArray.getJSONObject(i);
        municipalityRepository.save(
          new Municipality(
            jsonObject.getLong("kode"),
            jsonObject.getString("navn")
          )
        );
      }
    }

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
