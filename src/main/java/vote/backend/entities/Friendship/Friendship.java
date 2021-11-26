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
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "friendships", schema = "easyvote")
public class Friendship {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false)
  private Long id;

  @ManyToOne
  private User user1;

  @ManyToOne
  private User user2;

  @Column(unique = true)
  private Timestamp dateEstablished;

}
