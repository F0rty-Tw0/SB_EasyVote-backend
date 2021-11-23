package vote.backend.entities.User.Candidate;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vote.backend.entities.Municipality.Municipality;
import vote.backend.entities.Party.Party;
import vote.backend.entities.User.Nem.Nem;
import vote.backend.entities.User.Roles.Role;
import vote.backend.entities.User.User;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "candidates", schema = "easyvote")
public class Candidate extends User {

  @ManyToOne
  private Party party;

  public Candidate(String name, Long phoneNumber, String address, String zip, String email, Long cpr) {
    super (name, phoneNumber, address, zip, email, cpr);
    this.party = party;
  }
}
