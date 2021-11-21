package vote.backend.controllers.CandidateController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vote.backend.entities.Party.Party;
import vote.backend.entities.User.Candidate.Candidate;

import java.util.List;

@Api(
        tags = "Candidate",
        description = "- (OPTIONAL) A secured endpoint for <b>Candidates</b>, requires a role of <b>ADMIN, MANAGER, CUSTOMER</b> to operate! - <em>(This endpoint was created for the testing and learning purposes only)</em>"
)
@RequestMapping("/api/candidates")
public interface CandidateControllerInterface {

  @ApiOperation(
          value = " - Returns all of the Candidates",
          authorizations = { @Authorization(value = "jwtToken") },
          notes = "Execute to retrieve all <b>Actors</b>."
  )
  @GetMapping
  @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('CUSTOMER')")
  public List<Candidate> findAllCandidates();

  @ApiOperation(
          value = " - Returns the Candidateby the Id",
          authorizations = { @Authorization(value = "jwtToken") },
          notes = "Enter the <b>id</b> of a Party to retrieve a <b>Party</b> Object."
  )
  @GetMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('CUSTOMER')")
  public Party findCandidateById(@PathVariable Long id);

}
