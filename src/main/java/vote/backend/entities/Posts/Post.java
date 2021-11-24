package vote.backend.entities.Posts;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vote.backend.entities.Posts.Comments.Comment;

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
}
