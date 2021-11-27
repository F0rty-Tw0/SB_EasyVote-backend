package vote.backend.controllers.NemController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(
  tags = "Nem",
  description = "- (OPTIONAL) A secured endpoint for <b>Nem</b>, requires a role of <b>ADMIN</b> to operate!"
)
@RequestMapping("/api/nem")
public interface NemControllerInterface {
  @ApiOperation(
    value = " - Returns the Nem by Username",
    authorizations = { @Authorization(value = "jwtToken") },
    notes = "Enter the <b>Username</b> of a Nem to retrieve an <b>Nem</b> Object."
  )
  @GetMapping("/username/{username}")
  @PreAuthorize("hasRole('ADMIN')")
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
