package vote.backend.services.MunicipalityService;

import java.util.List;
import vote.backend.entities.Municipality.Municipality;

public interface MunicipalityService {
  public Municipality findMunicipalityById(Long id);

  public Municipality findMunicipalityByMunicipalityCode(Long code);

  public void delete(Long id);

  public List<Municipality> findAll();

  public Municipality findMunicipalityByZipCode(Long zipCode);
}
