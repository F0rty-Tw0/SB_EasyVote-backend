package vote.backend.entities.Party;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vote.backend.entities.User.Candidate.Candidate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "parties", schema = "easyvote")
public class Party {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false)
  private Long id;

  @Column(nullable = false)
  private Long partyName;

  // Letter of the party, like "SF" "A" "V"
  @Column(nullable = false)
  private String partyLetter;

  @ManyToMany
  private List<Candidate> candidates = new ArrayList<>();
}
