package vote.backend.entities.User.Nem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "nem", schema = "easyvote")
public class Nem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false)
  private Long id;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false, unique = true)
  private String username;

  public Nem(String password, String username) {
    this.password = password;
    this.username = username;
  }
}
