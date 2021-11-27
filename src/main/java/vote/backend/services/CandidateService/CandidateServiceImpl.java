package vote.backend.services.CandidateService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vote.backend.entities.Party.Party;
import vote.backend.entities.User.Candidate.Candidate;
import vote.backend.entities.User.Role.Role;
import vote.backend.errorHandler.ErrorResponseCreator;
import vote.backend.errorHandler.Exceptions.ResourceNotFoundException;
import vote.backend.repositories.CandidateRepository;

@Service
public class CandidateServiceImpl implements CandidateService {

  @Autowired
  private CandidateRepository candidateRepository;

  String object = "Candidate";

  @Override
  public List<Candidate> findAllCandidates() {
    return candidateRepository.findAll();
  }

  @Override
  public Candidate findCandidateById(Long id) {
    return candidateRepository
      .findById(id)
      .orElseThrow(
        () ->
          new ResourceNotFoundException(
            ErrorResponseCreator.notFoundException(object, "id", id)
          )
      );
  }

  @Override
  public void updateCandidateById(Long id) {}

  @Override
  public void updateCandidatePartyById(Long id, Party party) {
    Candidate candidate = candidateRepository
      .findById(id)
      .orElseThrow(
        () ->
          new ResourceNotFoundException(
            ErrorResponseCreator.notFoundException(object, "id", id)
          )
      );
    candidate.setParty(party);
    candidateRepository.save(candidate);
  }

  @Override
  public void updateCandidateRoleById(Long id, Role role) {
    Candidate candidateToUpdate = candidateRepository
      .findById(id)
      .orElseThrow(
        () ->
          new ResourceNotFoundException(
            ErrorResponseCreator.notFoundException(object, "id", id)
          )
      );
    candidateToUpdate.setRole(role);
    candidateRepository.save(candidateToUpdate);
  }

  @Override
  public void updateCandidateSloganById(Long id, String string) {
    Candidate candidateToUpdate = candidateRepository
      .findById(id)
      .orElseThrow(
        () ->
          new ResourceNotFoundException(
            ErrorResponseCreator.notFoundException(object, "id", id)
          )
      );
    candidateToUpdate.setSlogan(string);
    candidateRepository.save(candidateToUpdate);
  }

  @Override
  public void addCandidate(Candidate candidate) {
    candidateRepository.save(candidate);
  }

  @Override
  public void deleteCandidateById(Long id) {
    candidateRepository.deleteById(id);
  }
}
