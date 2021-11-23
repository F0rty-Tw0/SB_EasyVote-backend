package vote.backend.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vote.backend.entities.Municipality.Municipality;

@Repository
public interface MunicipalityRepository
  extends JpaRepository<Municipality, Long> {
  public Optional<Municipality> findById(Long id);

  public Optional<Municipality> findByCode(Long code);
}
