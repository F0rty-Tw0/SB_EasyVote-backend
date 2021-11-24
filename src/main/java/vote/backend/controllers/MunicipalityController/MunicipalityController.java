package vote.backend.controllers.MunicipalityController;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import vote.backend.entities.Municipality.Municipality;
import vote.backend.services.MunicipalityService.MunicipalityServiceImp;

@RestController
public class MunicipalityController implements MunicipalityControllerInterface {

  @Autowired
  MunicipalityServiceImp municipalityService;

  @Override
  public List<Municipality> findAllMunicipalities() {
    return municipalityService.findAllMunicipalities();
  }

  @Override
  public Municipality findMunicipalityById(Long id) {
    return municipalityService.findMunicipalityById(id);
  }

  @Override
  public Municipality findMunicipalityByMunicipalityCode(Long code) {
    return municipalityService.findMunicipalityByMunicipalityCode(code);
  }

  @Override
  public Municipality findMunicipalityByMunicipalityZipCode(Long zipCode) {
    return municipalityService.findMunicipalityByZipCode(zipCode);
  }
}
