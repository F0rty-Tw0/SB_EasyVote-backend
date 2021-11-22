package vote.backend.controllers.KommuneController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vote.backend.entities.Group.Kommune;
import vote.backend.entities.Party.Party;


@Api(
        tags = "Kommunes",
        description = "- (OPTIONAL) A secured endpoint for <b>Kommunes</b> requires a role of <b>ADMIN, MANAGER, CUSTOMER</b> to operate! -  <em> (This endpoint was created for the testing and learning purposes only)</em>"
)
@RequestMapping("/api/kommunes")
public interface KommuneControllerInterface {
    @ApiOperation(
            value = " - Returns all the Kommunes",
            authorizations = { @Authorization(value = "jwtToken") },
            notes = "Execute to retrieve all <b>Kommunes</b>."
    )
    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR') or hasRole('VOTER') or hasRole('CANDIDATE')")
    public List<Kommune> findAllKommunes();

    @ApiOperation(
            value = " - Returns the Candidate by the Id",
            authorizations = { @Authorization(value = "jwtToken") },
            notes = "Enter the <b>id</b> of a Kommune to retrieve a <b>Kommune</b> Object."
    )
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR') or hasRole('VOTER') or hasRole('CANDIDATE')")
    public Kommune findKommuneById(@PathVariable Long id);

    @ApiOperation(
            value = " - Returns the Candidate by the Id",
            authorizations = { @Authorization(value = "jwtToken") },
            notes = "Enter the <b>code</b> of a Kommune to retrieve a <b>Kommune</b> Object."
    )
    @GetMapping("/code/{code}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR') or hasRole('VOTER') or hasRole('CANDIDATE')")
    public Kommune findKommuneByKommuneCode(@PathVariable Long code);

    @ApiOperation(
            value = " - Returns the Candidate by the Id",
            authorizations = { @Authorization(value = "jwtToken") },
            notes = "Enter the <b>ZipCode</b> of a Kommune to retrieve a <b>Kommune</b> Object."
    )
    @GetMapping("/zip/{zipcode}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR') or hasRole('VOTER') or hasRole('CANDIDATE')")
    public String findKommuneByKommuneZipCode(@PathVariable Long zipcode);



}

