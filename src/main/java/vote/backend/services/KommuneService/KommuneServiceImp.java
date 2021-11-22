package vote.backend.services.KommuneService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import vote.backend.ErrorHandler.ResourceNotFoundException;
import vote.backend.entities.Group.Kommune;
import vote.backend.repositories.KommuneRepository;

import java.util.List;
import java.util.Optional;

@Service
public class KommuneServiceImp implements KommuneService {

    @Autowired
    KommuneRepository kommuneRepository;

    @Override
    public Kommune findKommuneById(Long id) {
        Kommune kommune = kommuneRepository.findKommuneById(id).orElseThrow(
                () -> new ResourceNotFoundException("Kommune with this id not found " + id)
        );
        return kommune;
    }

    @Override
    public Kommune findKommuneByKommuneCode(Long kommuneCode) {
        Kommune kommune = kommuneRepository.findKommuneByKommuneCode(kommuneCode)
                .orElseThrow(() -> new ResourceNotFoundException("Kommune whit this kommuneCode not found " + kommuneCode));
        return kommune;
    }

    @Override
    public void delete(Long id) {
        Optional<Kommune> kommune = kommuneRepository.findKommuneById(id);
        kommune.ifPresent(kommune1 -> kommuneRepository.delete(kommune1));
    }

    @Override
    public List<Kommune> findAll() {
        List<Kommune> allKommunes = kommuneRepository.findAll();
        return allKommunes;
    }

    @Override
    public String findKommuneByZipCode(Long zipCode) {
       String uri = "https://api.dataforsyningen.dk/postnumre?nr="+zipCode;
       RestTemplate restTemplate = new RestTemplate();
       String result = restTemplate.getForObject(uri,String.class);
        System.out.println(result);
       return result;
    }
}

