package vote.backend.services.MunicipalityService;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import vote.backend.entities.Municipality.Municipality;
import vote.backend.errorHandler.ResourceNotFoundException;
import vote.backend.repositories.MunicipalityRepository;

@Service
public class MunicipalityServiceImp implements MunicipalityService {

  @Autowired
  MunicipalityRepository municipalityRepository;

  @Override
  public Municipality findMunicipalityById(Long id) {
    return municipalityRepository
      .findById(id)
      .orElseThrow(
        () ->
          new ResourceNotFoundException(
            "Municipality with this id not found " + id
          )
      );
  }

  @Override
  public Municipality findMunicipalityByMunicipalityCode(Long code) {
    return municipalityRepository
      .findByCode(code)
      .orElseThrow(
        () ->
          new ResourceNotFoundException(
            "Municipality whit this municipalityCode not found " + code
          )
      );
  }

  @Override
  public void delete(Long id) {
    Optional<Municipality> municipality = municipalityRepository.findById(id);
    municipality.ifPresent(
      municipality1 -> municipalityRepository.delete(municipality1)
    );
  }

  @Override
  public List<Municipality> findAll() {
    return municipalityRepository.findAll();
  }

  @Override
  public String findMunicipalityByZipCode(Long zipCode) {
    String uri = "https://api.dataforsyningen.dk/postnumre?nr=" + zipCode;
    RestTemplate restTemplate = new RestTemplate();
    String result = restTemplate.getForObject(uri, String.class);
    System.out.println(result);
    return result;
  }
}
