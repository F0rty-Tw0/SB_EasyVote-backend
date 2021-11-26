package vote.backend.entities.Friendship;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vote.backend.entities.User.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "friendships", schema = "easyvote", uniqueConstraints={
        @UniqueConstraint(columnNames = {"user1_id", "user2_id"})
})
public class Friendship {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false)
  private Long id;

  @ManyToOne
  private User user1;

  @ManyToOne
  private User user2;

  @Column()
  private LocalDate dateEstablished;

  public Friendship(User user1, User user2, LocalDate dateEstablished) {
    this.user1 = user1;
    this.user2 = user2;
    this.dateEstablished = dateEstablished;
  }

}
