package vote.backend.services.KommuneService;

import vote.backend.entities.Group.Kommune;

import java.util.Optional;

public interface KommuneService {

   public Optional<Kommune> findKommuneById(Long id);
   public Optional <Kommune> findKommuneByKommuneCode(Long kommuneCode);
   public void delete(Long id);
}
