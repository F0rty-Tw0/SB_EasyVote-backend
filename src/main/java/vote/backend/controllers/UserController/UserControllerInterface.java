package vote.backend.controllers.UserController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import vote.backend.entities.User.User;

@Api(
  tags = "User",
  description = "- A secured endpoint for <b>Users</b>, requires a role of <b>ADMIN, MODERATOR, CANDIDATE or VOTER</b> to operate!"
)
@RequestMapping("/api/users")
public interface UserControllerInterface {
  @ApiOperation(
    value = " - Returns all of the Users",
    authorizations = { @Authorization(value = "jwtToken") },
    notes = "Execute to retrieve all <b>Users</b>."
  )
  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping
  public List<User> findAllUsers();

  @ApiOperation(
    value = " - Returns the authenticated user ('type=extended' - extends the returned data - Requires ADMIN rights)",
    authorizations = { @Authorization(value = "jwtToken") },
    notes = "Retrieve an <b>User</b> Object.<br><em>Requires a role of a minimum <b>VOTER</b></em>"
  )
  @GetMapping("/user")
  @PreAuthorize(
    "hasRole('ADMIN') or hasRole('MODERATOR') or hasRole('CANDIDATE') or hasRole('VOTER')"
  )
  public Object getUserFromToken(
    @RequestHeader(name = "Authorization", required = false) String token
  );

  @ApiOperation(
    value = " - Returns the User by the Id",
    authorizations = { @Authorization(value = "jwtToken") },
    notes = "Enter the <b>id</b> of a User to retrieve a <b>User</b> Object."
  )
  @GetMapping("/{id}")
  @PreAuthorize(
    "hasRole('ADMIN') or hasRole('MODERATOR') or hasRole('CANDIDATE') or hasRole('VOTER')"
  )
  public User findUserByNemId(@PathVariable Long id);
}
