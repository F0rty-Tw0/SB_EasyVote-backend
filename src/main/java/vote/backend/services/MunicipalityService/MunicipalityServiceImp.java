package vote.backend.services.MunicipalityService;

import java.util.List;
import java.util.Optional;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import vote.backend.entities.Municipality.Municipality;
import vote.backend.entities.Post.Post;
import vote.backend.entities.User.User;
import vote.backend.errorHandler.ErrorResponseCreator;
import vote.backend.errorHandler.Exceptions.ResourceNotFoundException;
import vote.backend.repositories.MunicipalityRepository;

@Service
public class MunicipalityServiceImp implements MunicipalityService {

  @Autowired
  MunicipalityRepository municipalityRepository;

  String object = "Municipality";

  @Override
  public List<Municipality> findAllMunicipalities() {
    return municipalityRepository.findAll();
  }

  @Override
  public Municipality findMunicipalityById(Long id) {
    return municipalityRepository
      .findById(id)
      .orElseThrow(
        () ->
          new ResourceNotFoundException(
            ErrorResponseCreator.notFoundException(object, "id", id)
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
            ErrorResponseCreator.notFoundException(object, "code", code)
          )
      );
  }

  @Override
  public Municipality findMunicipalityByZipCode(Long zipCode) {
    String uri = "https://api.dataforsyningen.dk/postnumre?nr=" + zipCode;
    RestTemplate restTemplate = new RestTemplate();
    String result;
    try {
      result = restTemplate.getForObject(uri, String.class);
    } catch (Exception e) {
      throw new ResourceNotFoundException(
        "Municipality whit this zipCode not found " + zipCode
      );
    }
    JSONArray jsonArray = new JSONArray(result);

    if (jsonArray.length() == 0) {
      throw new ResourceNotFoundException(
        "Municipality whit this zipCode not found " + zipCode
      );
    }

    JSONObject jsonObject = jsonArray.getJSONObject(0);

    JSONObject municipality = jsonObject
      .getJSONArray("kommuner")
      .getJSONObject(0);

    Long code = municipality.getLong("kode");

    return municipalityRepository
      .findByCode(code)
      .orElseThrow(
        () ->
          new ResourceNotFoundException(
            ErrorResponseCreator.notFoundException(object, "code", code)
          )
      );
  }

  @Override
  public void updateMunicipalityById(Long id, Municipality municipality) {
    Municipality municipalityToUpdate = municipalityRepository
      .findById(id)
      .orElseThrow(
        () ->
          new ResourceNotFoundException(
            ErrorResponseCreator.notFoundException(object, "id", id)
          )
      );
    municipalityToUpdate.setName(municipality.getName());
    municipalityToUpdate.setCode(municipality.getCode());
    municipalityRepository.save(municipalityToUpdate);
  }

  @Override
  public void addPostToMunicipality(Post post, Municipality municipality) {
    municipalityRepository.save(municipality);
  }

  @Override
  public void addUserToMunicipality(User user) {
    Municipality municipality = findMunicipalityByZipCode(
      Long.parseLong(user.getZip())
    );

    municipalityRepository.save(municipality);
  }

  @Override
  public void addMunicipality(Municipality municipality) {
    municipalityRepository.save(municipality);
  }

  @Override
  public void deleteMunicipalityById(Long id) {
    Optional<Municipality> municipality = municipalityRepository.findById(id);
    municipality.ifPresent(
      municipality1 -> municipalityRepository.delete(municipality1)
    );
  }
}
