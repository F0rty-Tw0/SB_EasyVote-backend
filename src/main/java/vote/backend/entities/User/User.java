package vote.backend.entities.User;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vote.backend.entities.Municipality.Municipality;
import vote.backend.entities.Posts.Comments.Comment;
import vote.backend.entities.User.Nem.Nem;
import vote.backend.entities.User.Roles.Role;

@Getter
@Setter
@NoArgsConstructor
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

  @ManyToOne
  private Nem nem;

  @ManyToOne
  private Role role;

  @ManyToOne
  private Municipality municipality;

  @ManyToMany
  private List<Comment> comments = new ArrayList<>();

  public User(Nem nem, Role role) {
    this.nem = nem;
    this.role = role;
  }

  public User(
    String name,
    Long phoneNumber,
    String address,
    String zip,
    String email,
    Long cpr
  ) {
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.address = address;
    this.zip = zip;
    this.email = email;
    this.cpr = cpr;
  }
}
