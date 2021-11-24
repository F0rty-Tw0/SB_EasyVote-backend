package vote.backend.services.MunicipalityService;

import java.util.List;
import vote.backend.entities.Municipality.Municipality;

public interface MunicipalityService {
  public List<Municipality> findAllMunicipalities();

  public Municipality findMunicipalityById(Long id);

  public Municipality findMunicipalityByMunicipalityCode(Long code);

  public Municipality findMunicipalityByZipCode(Long zipCode);

  public void addMunicipality(Municipality municipality);

  public void deleteMunicipalityById(Long id);
}
