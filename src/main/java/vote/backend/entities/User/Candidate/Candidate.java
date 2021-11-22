package vote.backend.entities.User.Candidate;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vote.backend.entities.Party.Party;
import vote.backend.entities.User.User;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "candidates", schema = "easyvote")
public class Candidate extends User {

  @ManyToOne
  private Party party;
}
