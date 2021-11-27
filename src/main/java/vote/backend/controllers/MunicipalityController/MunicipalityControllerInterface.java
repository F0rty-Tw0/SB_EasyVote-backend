package vote.backend.controllers.MunicipalityController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vote.backend.entities.Municipality.Municipality;

@Api(
  tags = "Municipalities",
  description = "- A secured endpoint for <b>Municipalities</b> requires a role of <b>ADMIN, MODERATOR, CANDIDATE, VOTER</b> to operate!"
)
@RequestMapping("/api/municipalities")
public interface MunicipalityControllerInterface {
  @ApiOperation(
    value = " - Returns all the Municipalities",
    authorizations = { @Authorization(value = "jwtToken") },
    notes = "Execute to retrieve all <b>Municipalities</b>."
  )
  @GetMapping
  @PreAuthorize(
    "hasRole('ADMIN') or hasRole('MODERATOR') or hasRole('CANDIDATE') or hasRole('VOTER')"
  )
  public List<Municipality> findAllMunicipalities();

  @ApiOperation(
    value = " - Returns the Candidate by the Id",
    authorizations = { @Authorization(value = "jwtToken") },
    notes = "Enter the <b>id</b> of a Municipality to retrieve a <b>Municipality</b> Object."
  )
  @GetMapping("/{id}")
  @PreAuthorize(
    "hasRole('ADMIN') or hasRole('MODERATOR') or hasRole('CANDIDATE') or hasRole('VOTER')"
  )
  public Municipality findMunicipalityById(@PathVariable Long id);

  @ApiOperation(
    value = " - Returns the Candidate by the Id",
    authorizations = { @Authorization(value = "jwtToken") },
    notes = "Enter the <b>code</b> of a Municipality to retrieve a <b>Municipality</b> Object."
  )
  @GetMapping("/code/{code}")
  @PreAuthorize(
    "hasRole('ADMIN') or hasRole('MODERATOR') or hasRole('CANDIDATE') or hasRole('VOTER')"
  )
  public Municipality findMunicipalityByMunicipalityCode(
    @PathVariable Long code
  );

  @ApiOperation(
    value = " - Returns the Candidate by the Id",
    authorizations = { @Authorization(value = "jwtToken") },
    notes = "Enter the <b>ZipCode</b> of a Municipality to retrieve a <b>Municipality</b> Object."
  )
  @GetMapping("/zip/{zipCode}")
  @PreAuthorize(
    "hasRole('ADMIN') or hasRole('MODERATOR') or hasRole('CANDIDATE') or hasRole('VOTER')"
  )
  public Municipality findMunicipalityByMunicipalityZipCode(
    @PathVariable Long zipCode
  );
}
