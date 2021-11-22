package vote.backend.services.KommuneService;

import vote.backend.entities.Group.Kommune;

import java.util.List;
import java.util.Optional;

public interface KommuneService {

   public Kommune findKommuneById(Long id);
   public Kommune findKommuneByKommuneCode(Long kommuneCode);
   public void delete(Long id);
   public List<Kommune> findAll();
   public String findKommuneByZipCode(Long zipCode);
}
