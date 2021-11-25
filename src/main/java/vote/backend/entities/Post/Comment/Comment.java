package vote.backend.entities.Post.Comment;

import java.time.LocalDateTime;

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
import org.hibernate.annotations.CreationTimestamp;
import vote.backend.entities.Post.Post;
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

  private String text;

  @ManyToOne
  private User author;

  private int likes;

  @ManyToOne
  private Post post;

  @CreationTimestamp
  private LocalDateTime dateCreated;

  @CreationTimestamp
  private LocalDateTime dateEdited;

  public Comment(User author) {
    this.author = author;
  }

  public Comment(User author, String text) {
    this.author = author;
    this.text = text;
  }

  public Comment(User author, Post post, String text) {
    this.author = author;
    this.post = post;
    this.text = text;
  }
}
