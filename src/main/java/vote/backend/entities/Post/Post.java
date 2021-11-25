package vote.backend.entities.Post;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vote.backend.entities.Municipality.Municipality;
import vote.backend.entities.Post.Comment.Comment;
import vote.backend.entities.User.User;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "posts", schema = "easyvote")
public class Post extends Comment {

  private String title;

  @ManyToOne
  private Municipality municipality;

  public Post(User author, String title) {
    super(author);
    this.municipality = author.getMunicipality();
    this.title = title;
  }
}
