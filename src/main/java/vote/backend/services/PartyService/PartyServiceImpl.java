package vote.backend.services.PartyService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vote.backend.entities.Party.Party;
import vote.backend.errorHandler.ErrorResponseCreator;
import vote.backend.errorHandler.Exceptions.ResourceNotFoundException;
import vote.backend.repositories.PartyRepository;

@Service
public class PartyServiceImpl implements PartyService {

  @Autowired
  private PartyRepository partyRepository;

  private String object = "Party";

  @Override
  public List<Party> findAllParties() {
    return partyRepository.findAll();
  }

  @Override
  public Party findPartyById(Long id) {
    return partyRepository
      .findById(id)
      .orElseThrow(
        () ->
          new ResourceNotFoundException(
            ErrorResponseCreator.notFoundException(object, "id", id)
          )
      );
  }

  @Override
  public Party findPartyByName(String name) {
    return partyRepository
      .findByName(name)
      .orElseThrow(
        () ->
          new ResourceNotFoundException(
            ErrorResponseCreator.notFoundException(object, "name", name)
          )
      );
  }

  @Override
  public void addParty(Party party) {
    partyRepository.save(party);
  }

  @Override
  public void deletePartyById(Long id) {
    partyRepository.deleteById(id);
  }
}
