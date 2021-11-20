package vote.backend.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


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

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique=true)
    private String email;

    @Column(nullable = false)
    private int phoneNumber;

    @Column(nullable = false)
    private String adress;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private Long cpr;

    @Column(nullable = false)
    private String role;

    public User(String fullName, String email, int phoneNumber, String adress, String password, Long cpr, String role) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.adress = adress;
        this.password = password;
        this.cpr = cpr;
        this.role = role;
    }
}
