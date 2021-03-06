package vote.backend.controllers.CandidateController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vote.backend.entities.User.Candidate.Candidate;

@Api(
  tags = "Candidate",
  description = "- A secured endpoint for <b>Candidates</b>, requires a role of <b>ADMIN, MODERATOR</b> to operate!"
)
@RequestMapping("/api/candidates")
public interface CandidateControllerInterface {
  @ApiOperation(
    value = " - Returns all of the Candidates",
    authorizations = { @Authorization(value = "jwtToken") },
    notes = "Execute to retrieve all <b>Candidates</b>."
  )
  @GetMapping
  @PreAuthorize(
          "hasRole('ADMIN') or hasRole('MODERATOR') or hasRole('VOTER') or hasRole('CANDIDATE')"
  )
  public List<Candidate> findAllCandidates();

  @ApiOperation(
    value = " - Returns the Candidate by the Id",
    authorizations = { @Authorization(value = "jwtToken") },
    notes = "Enter the <b>id</b> of a Candidate to retrieve a <b>Candidate</b> Object."
  )
  @GetMapping("/{id}")
  @PreAuthorize(
          "hasRole('ADMIN') or hasRole('MODERATOR') or hasRole('VOTER') or hasRole('CANDIDATE')"
  )
  public Object findCandidateById(@PathVariable Long id);
}
