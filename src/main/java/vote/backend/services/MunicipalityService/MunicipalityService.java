package vote.backend.services.MunicipalityService;

import java.util.List;
import vote.backend.entities.Municipality.Municipality;
import vote.backend.entities.Post.Post;
import vote.backend.entities.User.User;

public interface MunicipalityService {
  public List<Municipality> findAllMunicipalities();

  public Municipality findMunicipalityById(Long id);

  public Municipality findMunicipalityByMunicipalityCode(Long code);

  public Municipality findMunicipalityByZipCode(Long zipCode);

  public void updateMunicipalityById(Long id, Municipality municipality);

  public void addUserToMunicipality(User user, Municipality municipality);

  public void addPostToMunicipality(Post posts, Municipality municipality);

  public void addMunicipality(Municipality municipality);

  public void deleteMunicipalityById(Long id);
}
