package vote.backend.entities.Post;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vote.backend.entities.Post.Comment.Comment;
import vote.backend.entities.User.User;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "posts", schema = "easyvote")
public class Post extends Comment {

  private String title;

  @ManyToMany
  @JoinTable(
    name = "post_comments",
    joinColumns = @JoinColumn(name = "post_id"),
    inverseJoinColumns = @JoinColumn(name = "comment_id")
  )
  private List<Comment> comments = new ArrayList<>();

  public Post(User author, String title) {
    super(author);
    this.title = title;
  }

  public void addComment(Comment comment) {
    comment.setPost(this);
    comments.add(comment);
  }
}
