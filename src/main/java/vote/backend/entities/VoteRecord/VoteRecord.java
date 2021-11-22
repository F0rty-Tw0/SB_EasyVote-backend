package vote.backend.entities.VoteRecord;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vote.backend.entities.User.Candidate.Candidate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDate;


@NoArgsConstructor
@Getter
@Setter
@Entity
public class VoteRecord {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false)
  private Long id;

//  @Id
  @OneToOne
  private Candidate candidate;

  @Column
  private Long voteCount;

  @Column
  private LocalDate date;

}
