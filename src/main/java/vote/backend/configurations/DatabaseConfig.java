package vote.backend.configurations;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import vote.backend.entities.Municipality.Municipality;
import vote.backend.entities.Party.Party;
import vote.backend.entities.User.Candidate.Candidate;
import vote.backend.entities.User.Roles.ERoles;
import vote.backend.entities.User.Roles.Role;
import vote.backend.entities.User.User;
import vote.backend.entities.VoteRecord.VoteRecord;
import vote.backend.repositories.MunicipalityRepository;
import vote.backend.repositories.NemRepository;
import vote.backend.repositories.RoleRepository;
import vote.backend.repositories.UserRepository;
import vote.backend.security.AuthenticationPayload.Request.LoginRequest;
import vote.backend.security.AuthenticationPayload.Request.SignupRequest;
import vote.backend.security.AuthenticationPayload.Response.JwtResponse;
import vote.backend.services.AuthService.AuthService;
import vote.backend.services.CandidateService.CandidateService;
import vote.backend.services.PartyService.PartyService;
import vote.backend.services.VoteRecordService.VoteRecordService;

import java.time.LocalDate;

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

  @Autowired
  private PartyService partyService;

  @Autowired
  private CandidateService candidateService;

  @Autowired
  private VoteRecordService voteRecordService;

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

    if (partyService.findAll().isEmpty()) {
      partyService.addParty(new Party("Liberal Alliance", "LA"));
      partyService.addParty(new Party("Enhedslisten", "Ø"));
      partyService.addParty(new Party("Radikale Venstre", "B"));
      partyService.addParty(new Party("Det Konservative Folkeparti", "C"));
      partyService.addParty(new Party("Nye Borgerlige", "D"));
      partyService.addParty(new Party("Socialistisk Folkeparti", "SF"));
      partyService.addParty(new Party("Veganerpartiet", "G"));
      partyService.addParty(new Party("Frie Grønne, Danmarks Nye Venstrefløjsparti", "Q"));
      partyService.addParty(new Party("Kristendemokraterne", "K"));
      partyService.addParty(new Party("Dansk Folkeparti", "O"));
      partyService.addParty(new Party("Venstre, Danmarks Liberale Parti", "V"));
      partyService.addParty(new Party("Alternativet", "Å"));


    }

    if (candidateService.findAll().isEmpty()) {
      LoginRequest loginRequest = new LoginRequest("admin", "test");
      ResponseEntity<JwtResponse> authentication = authService.authenticateUser(loginRequest);
      Long nemId = authentication.getBody().getId();

      User user = userRepository.findByNemId(nemId).get();
      userRepository.convertUserToCandidate(user.getId());
      Role role = roleRepository.getById(3L);
      Party party = partyService.findPartyByName("Veganerpartiet");
        candidateService.updateCandidatePartyById(user.getId(),party);
      candidateService.updateCandidateRoleById(user.getId(), role);
    }

    if (voteRecordService.findAllVoteRecords().isEmpty()) {
      Candidate candidate = candidateService.findCandidateById(1L);

      voteRecordService.addVoteRecord(new VoteRecord(candidate, 1L, LocalDate.of(2015, 02, 20)

      ));


      }

  }
}
