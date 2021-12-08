package vote.backend.controllers.FriendshipController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
