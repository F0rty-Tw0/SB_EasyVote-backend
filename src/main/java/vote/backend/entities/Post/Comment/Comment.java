package vote.backend.entities.Post.Comment;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vote.backend.entities.User.User;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "comments", schema = "easyvote")
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false)
  private Long id;

  @Column(nullable = false)
  private String text;

  @ManyToOne
  private User author;

  private int likes;

  private LocalDate dateCreated;
  private LocalDate dateEdited;
}
