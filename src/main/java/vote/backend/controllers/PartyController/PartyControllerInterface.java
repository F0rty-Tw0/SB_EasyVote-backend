package vote.backend.controllers.PartyController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vote.backend.entities.Party.Party;

import java.util.List;

@Api(
        tags = "Party",
        description = "- (OPTIONAL) A secured endpoint for <b>Parties</b>, requires a role of <b>ADMIN, MANAGER, CUSTOMER</b> to operate! - <em>(This endpoint was created for the testing and learning purposes only)</em>"
)
@RequestMapping("/api/parties")
public interface PartyControllerInterface {
  @ApiOperation(
          value = " - Returns all of the Parties",
          authorizations = { @Authorization(value = "jwtToken") },
          notes = "Execute to retrieve all <b>Parties</b>."
  )
  @GetMapping
//  @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('CUSTOMER')")
  public List<Party> findAllParties();

  @ApiOperation(
          value = " - Returns the Party by the Id",
          authorizations = { @Authorization(value = "jwtToken") },
          notes = "Enter the <b>id</b> of a Party to retrieve a <b>Party</b> Object."
  )
  @GetMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('CUSTOMER')")
  public Party findPartyById(@PathVariable Long id);
}
