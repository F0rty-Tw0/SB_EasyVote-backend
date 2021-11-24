package vote.backend.controllers.VoteRecordController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import java.time.LocalDate;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vote.backend.entities.VoteRecord.VoteRecord;

@Api(
  tags = "VoteRecord",
  description = "- (OPTIONAL) A secured endpoint for <b>Parties</b>, requires a role of <b>ADMIN, MANAGER, CUSTOMER</b> to operate! - <em>(This endpoint was created for the testing and learning purposes only)</em>"
)
@RequestMapping("/api/vote-records")
public interface VoteRecordControllerInterface {
  @ApiOperation(
    value = " - Returns all of the Vote records",
    authorizations = { @Authorization(value = "jwtToken") },
    notes = "Execute to retrieve all <b>Vote records</b>."
  )
  @GetMapping
  @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('CUSTOMER')")
  public List<VoteRecord> findAllVoteRecords();

  @ApiOperation(
    value = " - Returns the Vote record by the Id",
    authorizations = { @Authorization(value = "jwtToken") },
    notes = "Enter the <b>id</b> of a Vote record to retrieve a <b>VoteRecord</b> Object."
  )
  @GetMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('CUSTOMER')")
  public VoteRecord findVoteRecordById(@PathVariable Long id);

  @ApiOperation(
    value = " - Returns the Vote record by the date",
    authorizations = { @Authorization(value = "jwtToken") },
    notes = "Enter the <b>date</b> of a Vote record to retrieve a <b>VoteRecord</b> Object."
  )
  @GetMapping("/{date}")
  @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('CUSTOMER')")
  public VoteRecord findVoteRecordByDebateDate(@PathVariable LocalDate date);

  @ApiOperation(
    value = " - Increments the vote of a given candidate by the id",
    authorizations = { @Authorization(value = "jwtToken") },
    notes = "Enter the <b>id</b> of a candidate to increment a <b>VoteRecord</b> Object."
  )
  @GetMapping("/vote-count/{id}")
  @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('CUSTOMER')")
  public void incrementVoteCountByCandidateId(@PathVariable Long id);
}
