package vote.backend.controllers.FriendshipController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import vote.backend.entities.Friendship.Friendship;


import java.util.List;

@Api(
        tags = "Friendship",
        description = "- (OPTIONAL) A secured endpoint for <b>Friendships</b>, requires a role of <b>ADMIN, MANAGER, CUSTOMER</b> to operate! - <em>(This endpoint was created for the testing and learning purposes only)</em>"
)
@RequestMapping("/api/friendships")
public interface FriendshipControllerInterface {
  @ApiOperation(
          value = " - Returns all of the Friendships",
          authorizations = { @Authorization(value = "jwtToken") },
          notes = "Execute to retrieve all <b>Friendships</b>."
  )
  @GetMapping
  @PreAuthorize(
          "hasRole('ADMIN') or hasRole('MODERATOR') or hasRole('VOTER') or hasRole('CANDIDATE')"
  )
  public List<Friendship> findAllFriendships();

  @ApiOperation(
          value = " - Returns the Friendships by a single email",
          authorizations = { @Authorization(value = "jwtToken") },
          notes = "Enter the <b>email</b> of a user to retrieve all <b>Friendships</b>."
  )
  @GetMapping("/{email}")
  @PreAuthorize(
          "hasRole('ADMIN') or hasRole('MODERATOR') or hasRole('VOTER') or hasRole('CANDIDATE')"
  )
  public List<Friendship> findFriendshipsByEmail(@PathVariable String email);

  @ApiOperation(
          value = " - Returns the Friendshipsby two emails",
          authorizations = { @Authorization(value = "jwtToken") },
          notes = "Enter the <b>email</b> of two users to see if they have a friendship</b>."
  )
  @GetMapping("/{email1}/{email2}")
  @PreAuthorize(
          "hasRole('ADMIN') or hasRole('MODERATOR') or hasRole('VOTER') or hasRole('CANDIDATE')"
  )
  public Object findFriendShipByUser1AndUser2(@PathVariable String email1, @PathVariable String email2);

  @ApiOperation(
          value = " - Adds a Friendship to the database",
          authorizations = { @Authorization(value = "jwtToken") },
          notes = "Enter the <b>Email of two user</b> in the body in order to create a new <b>Friendship</b>."
  )
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize(
          "hasRole('ADMIN') or hasRole('MODERATOR') or hasRole('VOTER') or hasRole('CANDIDATE')"
  )
  public void addFriendship(@RequestBody Friendship friendship);

}
