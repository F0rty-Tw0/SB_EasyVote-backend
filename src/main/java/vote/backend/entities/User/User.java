package vote.backend.entities.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import vote.backend.entities.Post.Comment.Comment;
import vote.backend.entities.Post.Post;
import vote.backend.entities.User.Nem.Nem;
import vote.backend.entities.User.Role.Role;

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

  private LocalDate birthDate;

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

  @ManyToMany
  private List<Post> posts = new ArrayList<>();

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

  public void addComment(Comment comment) {
    this.comments.add(comment);
  }

  public void addPost(Post post) {
    this.posts.add(post);
  }
}
