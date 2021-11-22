package vote.backend.entities.User;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vote.backend.entities.User.Nem.Nem;
import vote.backend.entities.User.Roles.Role;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users", schema = "easyvote")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false)
  private Long id;

  private String name;

  private Long phoneNumber;

  private String address;

  private String zip;

  @Column(unique = true)
  private String email;

  @Column(unique = true)
  private Long cpr;

  @OneToOne
  private Nem nem;

  @ManyToOne
  @JsonIgnoreProperties("id")
  private Role role;
}
