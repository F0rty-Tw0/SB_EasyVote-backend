package vote.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vote.backend.entities.Group.Kommune;

import java.util.Optional;

@Repository
public interface KommuneRepository extends JpaRepository<Kommune, Long> {

    public Optional<Kommune> findKommuneById(Long id);
    public Optional <Kommune> findKommuneByKommuneCode(Long kommuneCode);

}
