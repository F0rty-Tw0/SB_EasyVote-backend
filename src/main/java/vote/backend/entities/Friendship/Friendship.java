package vote.backend.entities.Friendship;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "friendships", schema = "easyvote", uniqueConstraints={
        @UniqueConstraint(columnNames = {"email1", "email2"})
})
public class Friendship {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false)
  private Long id;

  @Column(nullable = false)
  private String email1;

  @Column(nullable = false)
  private String email2;

  @Column(columnDefinition = "pending")
  private String status;

  @Column()
  private LocalDate dateEstablished;

  public Friendship(String email1, String email2, LocalDate dateEstablished) {
    this.email1 = email1;
    this.email2 = email2;
    this.dateEstablished = dateEstablished;
  }

}
