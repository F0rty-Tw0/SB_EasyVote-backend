package vote.backend.entities.VoteRecord;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vote.backend.entities.User.Candidate.Candidate;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(
  uniqueConstraints = {
    @UniqueConstraint(
      name = "UniqueCandidateIdAndDate",
      columnNames = { "candidate_id", "debateDate" }
    ),
  },
  name = "vote_records",
  schema = "easyvote"
)
public class VoteRecord {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false)
  private Long id;

  @OneToOne
  private Candidate candidate;

  private Long voteCount;

  @Column(unique = true)
  private LocalDate debateDate;

  public VoteRecord(Candidate candidate, Long voteCount, LocalDate debateDate){
    this.candidate = candidate;
    this.voteCount = voteCount;
    this.debateDate = debateDate;
  }

  public Long incrementVoteCount(Long voteCount){
    this.voteCount = voteCount;
      return voteCount++;

  }

}
