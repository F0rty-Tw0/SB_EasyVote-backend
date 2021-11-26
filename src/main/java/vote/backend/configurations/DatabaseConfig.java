package vote.backend.configurations;

import java.time.LocalDate;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import vote.backend.entities.Friendship.Friendship;
import vote.backend.entities.Municipality.Municipality;
import vote.backend.entities.Party.Party;
import vote.backend.entities.Post.Comment.Comment;
import vote.backend.entities.Post.Post;
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
import vote.backend.services.CommentService.CommentService;
import vote.backend.services.FriendshipService.FriendshipService;
import vote.backend.services.MunicipalityService.MunicipalityService;
import vote.backend.services.NemService.NemService;
import vote.backend.services.PartyService.PartyService;
import vote.backend.services.PostService.PostService;
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

  @Autowired
  private PostService postService;

  @Autowired
  private CommentService commentService;

  @Autowired
  private FriendshipService friendshipService;

  @Override
  public void run(String... args) throws Exception {
    createNems();
    createRoles();
    createMunicipalities();
    createParties();
    updateUser();
    createCandidates();
    createVoteRecords();
    createPosts();
    createComments();
    createFriendship();
  }

  private void createNems() {
    if (nemService.findAllNems().isEmpty()) {
      authService.registerNem(new SignupRequest("admin", "test"));
      authService.registerNem(new SignupRequest("admin2", "test2"));
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

  private void updateUser() {
    User user = getLoggedUser();
    user.setName("Artiom Tofan");
    user.setPhoneNumber(60902086L);
    user.setCpr(2727272727L);
    user.setAddress("Saxogade 25, 1 TH");
    user.setEmail("artm@gmail.com");
    user.setBirthDate(LocalDate.of(1996, 12, 12));
    user.setZip("3000");
    userService.updateUser(user.getId(), user);
  }

  private void createCandidates() {
    if (candidateService.findAllCandidates().isEmpty()) {
      User user = getLoggedUser();
      userService.convertUserToCandidate(user);
      Party party = partyService.findPartyByName("Veganerpartiet");
      candidateService.updateCandidatePartyById(user.getId(), party);
      candidateService.updateCandidateSloganById(
              user.getId(),
              "Veganerpartiet er en kandidat til venstre"
      );
    }
  }

  private void createVoteRecords() {
    if (voteRecordService.findAllVoteRecords().isEmpty()) {
      Candidate candidate = candidateService.findCandidateById(1L);

      voteRecordService.addVoteRecord(
              new VoteRecord(candidate, LocalDate.of(2021, 11, 20))
      );
    }
  }

  private void createPosts() {
    if (postService.findAllPosts().isEmpty()) {
      User user = getLoggedUser();
      postService.addPost(new Post(user, "Post 1"));
      postService.addPost(new Post(user, "Post 2"));
      postService.addPost(new Post(user, "Post 3"));
      postService.addPost(new Post(user, "Post 4"));
      postService.addPost(new Post(user, "Post 5"));
      List<Post> posts = postService.findPostsByAuthorZipCode(user.getZip());
      for (Post post : posts) {
        Municipality municipality = municipalityService.findMunicipalityByZipCode(
                Long.parseLong(user.getZip())
        );
        municipalityService.addPostToMunicipality(post, municipality);
      }
    }
  }

  private void createComments() {
    User user = getLoggedUser();
    Post post = postService.findPostByTitle("Post 1");
    commentService.addComment(new Comment(user, post, "Comment 1"));
    commentService.addComment(new Comment(user, post, "Comment 2"));
  }

  private User getLoggedUser() {
    LoginRequest loginRequest = new LoginRequest("admin", "test");
    ResponseEntity<JwtResponse> authentication = authService.authenticateUser(
            loginRequest
    );

    Long nemId = authentication.getBody().getId();
    return userService.findUserByNemId(nemId);
  }

  private void createFriendship() {
    if (friendshipService.findAllFriendships().isEmpty()) {
      User user1 = getLoggedUser();

      // The reason I am creating a new user here instead of using a method is because
      // I wasn't sure, since getLoggedUser() only gets 1 user from the loginRequest
      LoginRequest loginRequest2 = new LoginRequest("admin2", "test2");
      ResponseEntity<JwtResponse> authentication2 = authService.authenticateUser(
              loginRequest2);
      Long nemId2 = authentication2.getBody().getId();
      User user2 = userService.findUserByNemId(nemId2);
      user2.setName("Nikolai TheGreatest");
      user2.setPhoneNumber(20304050L);
      user2.setCpr(2626040400L);
      user2.setAddress("BigManGade");
      user2.setEmail("GalEnMail@gmail.com");
      user2.setBirthDate(LocalDate.of(1996, 12, 12));
      user2.setZip("3000");
      userService.updateUser(user2.getId(), user2);

      // TO DO > Avoid duplicates
//      if (friendshipService.ifFriendshipExists(user1.getId(), user2.getId()) == false) {
        friendshipService.addFriendship(new Friendship(user1, user2, LocalDate.of(2021, 11, 20)));
      }
    }
  }
