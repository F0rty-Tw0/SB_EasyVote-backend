package vote.backend.controllers.NemController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(
  tags = "Users",
  description = "- (OPTIONAL) A secured endpoint for <b>Users</b>, requires a role of <b>ADMIN, MANAGER, CUSTOMER</b> to operate! - <em>(This endpoint was created for the testing and learning purposes only).</em>"
)
@RequestMapping("/api/users")
public interface NemControllerInterface {
  @ApiOperation(
    value = " - Returns the Nem by Username",
    authorizations = { @Authorization(value = "jwtToken") },
    notes = "Enter the <b>Username</b> of a Nem to retrieve an <b>Nem</b> Object.<br><em>Requires a role of a minimum <b>CUSTOMER</b></em>"
  )
  @GetMapping("/username/{username}")
  @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('CUSTOMER')")
  public Object findNemByUsername(String username);

  @ApiOperation(
    value = " - Checks if the Nem exists with the Username",
    authorizations = { @Authorization(value = "jwtToken") },
    notes = "Enter the <b>Username</b> of a Nem to check if Nem exists."
  )
  @GetMapping("/exists/{username}")
  @PreAuthorize("hasRole('ADMIN')")
  public Boolean nemExistsByUsername(String username);
}
