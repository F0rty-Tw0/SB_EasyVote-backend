package vote.backend.configurations;

import java.time.LocalDate;
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
import vote.backend.entities.User.Role.ERoles;
import vote.backend.entities.User.Role.Role;
import vote.backend.entities.User.User;
import vote.backend.entities.VoteRecord.VoteRecord;
import vote.backend.security.AuthenticationPayload.Request.LoginRequest;
import vote.backend.security.AuthenticationPayload.Request.SignupRequest;
import vote.backend.security.AuthenticationPayload.Response.JwtResponse;
import vote.backend.services.AuthService.AuthService;
import vote.backend.services.CandidateService.CandidateService;
import vote.backend.services.MunicipalityService.MunicipalityService;
import vote.backend.services.NemService.NemService;
import vote.backend.services.PartyService.PartyService;
import vote.backend.services.RoleService.RoleService;
import vote.backend.services.UserService.UserService;
import vote.backend.services.VoteRecordService.VoteRecordService;

@Component
@Profile("!test")
public class DatabaseConfig implements CommandLineRunner {

  @Autowired
  private UserService userService;

  @Autowired
  private AuthService authService;

  @Autowired
  private NemService nemService;

  @Autowired
  private RoleService roleService;

  @Autowired
  private MunicipalityService municipalityService;

  @Autowired
  private PartyService partyService;

  @Autowired
  private CandidateService candidateService;

  @Autowired
  private VoteRecordService voteRecordService;

  @Override
  public void run(String... args) throws Exception {
    createNems();
    createRoles();
    createMunicipalities();
    createParties();
    createCandidates();
    createVoteRecords();
  }

  private void createNems() {
    if (nemService.findAllNems().isEmpty()) {
      authService.registerNem(new SignupRequest("admin", "test"));
    }
  }

  private void createRoles() {
    if (roleService.findAllRoles().isEmpty()) {
      ERoles[] roles = ERoles.values();
      for (int i = 0; i < roles.length; i++) {
        roleService.addRole(new Role(roles[i]));
      }
    }
  }

  private void createMunicipalities() {
    if (municipalityService.findAllMunicipalities().isEmpty()) {
      String url = "https://api.dataforsyningen.dk/kommuner";

      RestTemplate restTemplate = new RestTemplate();
      String result = restTemplate.getForObject(url, String.class);

      JSONArray jsonArray = new JSONArray(result);

      for (int i = 0; i < jsonArray.length(); i++) {
        JSONObject jsonObject = jsonArray.getJSONObject(i);
        municipalityService.addMunicipality(
          new Municipality(
            jsonObject.getLong("kode"),
            jsonObject.getString("navn")
          )
        );
      }
    }
  }

  private void createParties() {
    if (partyService.findAllParties().isEmpty()) {
      partyService.addParty(new Party("Liberal Alliance", "LA"));
      partyService.addParty(new Party("Enhedslisten", "Ø"));
      partyService.addParty(new Party("Radikale Venstre", "B"));
      partyService.addParty(new Party("Det Konservative Folkeparti", "C"));
      partyService.addParty(new Party("Nye Borgerlige", "D"));
      partyService.addParty(new Party("Socialistisk Folkeparti", "SF"));
      partyService.addParty(new Party("Veganerpartiet", "G"));
      partyService.addParty(
        new Party("Frie Grønne, Danmarks Nye Venstrefløjsparti", "Q")
      );
      partyService.addParty(new Party("Kristendemokraterne", "K"));
      partyService.addParty(new Party("Dansk Folkeparti", "O"));
      partyService.addParty(new Party("Venstre, Danmarks Liberale Parti", "V"));
      partyService.addParty(new Party("Alternativet", "Å"));
    }
  }

  private void createCandidates() {
    if (candidateService.findAllCandidates().isEmpty()) {
      LoginRequest loginRequest = new LoginRequest("admin", "test");
      ResponseEntity<JwtResponse> authentication = authService.authenticateUser(
        loginRequest
      );

      Long nemId = authentication.getBody().getId();

      User user = userService.findUserByNemId(nemId);
      userService.convertUserToCandidate(user.getId());
      Role role = roleService.findRoleById(3L);
      Party party = partyService.findPartyByName("Veganerpartiet");
      candidateService.updateCandidatePartyById(user.getId(), party);
      candidateService.updateCandidateRoleById(user.getId(), role);
      candidateService.updateCandidateSloganById(user.getId(), "Help the climate, eat green!");
    }
  }

  private void createVoteRecords() {
    if (voteRecordService.findAllVoteRecords().isEmpty()) {
      Candidate candidate = candidateService.findCandidateById(1L);

      voteRecordService.addVoteRecord(
        new VoteRecord(candidate, 1L, LocalDate.of(2015, 02, 20))
      );
    }
  }
}
