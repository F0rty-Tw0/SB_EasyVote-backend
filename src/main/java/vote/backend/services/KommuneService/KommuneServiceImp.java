package vote.backend.services.KommuneService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vote.backend.entities.Group.Kommune;
import vote.backend.repositories.KommuneRepository;

import java.util.Optional;

@Service
public class KommuneServiceImp implements KommuneService{

    @Autowired
    KommuneRepository kommuneRepository;

    @Override
    public Optional<Kommune> findKommuneById(Long id) {
        return kommuneRepository.findKommuneById(id);
    }

    @Override
    public Optional<Kommune> findKommuneByKommuneCode(Long kommuneCode) {
        return kommuneRepository.findKommuneByKommuneCode(kommuneCode);
    }

    @Override
    public void delete(Long id) {
        Optional<Kommune> kommune = kommuneRepository.findKommuneById(id);
        kommune.ifPresent(kommune1 -> kommuneRepository.delete(kommune1));
    }
}
